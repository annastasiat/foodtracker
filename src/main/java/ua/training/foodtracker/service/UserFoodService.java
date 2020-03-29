package ua.training.foodtracker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.foodtracker.dto.*;
import ua.training.foodtracker.entity.*;
import ua.training.foodtracker.exception.FoodNotExistsException;
import ua.training.foodtracker.exception.UserNotExistsException;
import ua.training.foodtracker.repository.UserFoodRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserFoodService {
    @Autowired
    private UserFoodRepository userFoodRepository;
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;

    private String getPrincipalUsername() {
        return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    @Transactional
    public UserFood save(UserMealDTO userMealDto) throws FoodNotExistsException, UserNotExistsException {

        Food food = foodService.findByName(userMealDto.getFoodName()).orElseThrow(FoodNotExistsException::new);
        User user = userService.findByUsername(getPrincipalUsername()).orElseThrow(UserNotExistsException::new);

        return userFoodRepository.save(UserFood.builder()
                .user(user)
                .food(food)
                .amount(userMealDto.getAmount())
                .date(Date.valueOf(LocalDate.now()))
                .build());
    }

    public MealsDTO getAllUsersFoodForAdmin() {
        return MealsDTO.builder().meals(
                userFoodRepository.findAll()
                        .stream()
                        .map(MealDTO::new)
                        .sorted(Comparator.comparing(MealDTO::getDate, Comparator.reverseOrder()))
                        .collect(Collectors.toList()))
                .build();
    }

    public UsersMealStatDTO findAllTodaysPrincipalStat() {
        List<UserMealStatDTO> todaysFood = userFoodRepository
                .findByUser_UsernameAndDate(getPrincipalUsername(), Date.valueOf(LocalDate.now()))
                .stream()
                .map(UserMealStatDTO::new)
                .collect(Collectors.toList());

        return UsersMealStatDTO.builder().usersFood(todaysFood).build();
    }

    public UsersMealStatDTO findAllPrincipalStat() {
        return UsersMealStatDTO.builder().usersFood(
                userFoodRepository.findByUser_Username(getPrincipalUsername())
                        .stream()
                        .map(UserMealStatDTO::new)
                        .sorted(Comparator.comparing(UserMealStatDTO::getDate, Comparator.reverseOrder()))
                        .collect(Collectors.toList()))
                .build();
    }

    public int countNorm() throws UserNotExistsException {
        User user = userService.findByUsername(getPrincipalUsername())
                .orElseThrow(UserNotExistsException::new);

        return (int) (ActivityLevel.valueOf(user.getActivityLevel()).getValue()
                * (Gender.valueOf(user.getGender()).getValue()
                + 10 * user.getWeight()
                + 6.25 * user.getHeight()
                - 5 * user.getAge()));
    }

    private int todaysFoodElement(Function<Food, Integer> foodGetter) {
        return userFoodRepository
                .findByUser_Username(getPrincipalUsername())
                .stream()
                .mapToInt(userFood -> foodGetter.apply(userFood.getFood()) * userFood.getAmount() / 100)
                .reduce(Integer::sum).orElse(0);
    }

    public int todaysCalories() {
        return todaysFoodElement(Food::getCalories);
    }

    public UserTodayStatisticsDTO getTodaysPrincipalStatistics() throws UserNotExistsException {
        return UserTodayStatisticsDTO.builder()
                .calories(todaysCalories())
                .carbs(todaysFoodElement(Food::getCarbs))
                .protein(todaysFoodElement(Food::getProtein))
                .fat(todaysFoodElement(Food::getFat))
                .caloriesNorm(countNorm())
                .build();
    }
}
