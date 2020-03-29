package ua.training.foodtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.training.foodtracker.dto.UserTodayStatisticsDTO;
import ua.training.foodtracker.entity.ActivityLevel;
import ua.training.foodtracker.entity.Gender;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserDetailsImpl;
import ua.training.foodtracker.repository.UserRepository;

import java.util.Optional;
import java.util.function.BiFunction;

@Service
public class UserCounting {

    @Autowired
    private UserFoodService userFoodService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;


    public int countNorm() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(((UserDetailsImpl) principal).getUsername()).get();
        return (int) (ActivityLevel.valueOf(user.getActivityLevel()).getValue()
                * (Gender.valueOf(user.getGender()).getValue()
                + 10 * user.getWeight()
                + 6.25 * user.getHeight()
                - 5 * user.getAge()));


    }

    public int todaysCalories() {
        return userFoodService.findAllTodays().getUsersFood()
                .stream()
                .mapToInt(food -> foodService.findByName(food.getFoodname()).get().getCalories() * food.getAmount() / 100)
                .reduce(Integer::sum).orElse(0);
    }

    public int todaysProteins() {
        return userFoodService.findAllTodays().getUsersFood()
                .stream()
                .mapToInt(food -> foodService.findByName(food.getFoodname()).get().getProtein() * food.getAmount() / 100)
                .reduce(Integer::sum).orElse(0);
    }

    public int todaysCarbs() {
        return userFoodService.findAllTodays().getUsersFood()
                .stream()
                .mapToInt(food -> foodService.findByName(food.getFoodname()).get().getCarbs() * food.getAmount() / 100)
                .reduce(Integer::sum).orElse(0);
    }

    public int todaysFats() {
        return userFoodService.findAllTodays().getUsersFood()
                .stream()
                .mapToInt(food -> foodService.findByName(food.getFoodname()).get().getFat() * food.getAmount() / 100)
                .reduce(Integer::sum).orElse(0);
    }

    public UserTodayStatisticsDTO getTodaysUserStatistics(){
        return UserTodayStatisticsDTO
                .builder()
                .calories(todaysCalories())
                .carbs(todaysCarbs())
                .protein(todaysProteins())
                .fat(todaysFats())
                .caloriesNorm(countNorm())
                .build();
    }

}
