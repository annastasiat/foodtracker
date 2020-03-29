package ua.training.foodtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.foodtracker.config.SecurityConfiguration;
import ua.training.foodtracker.dto.UserFoodDTO;
import ua.training.foodtracker.dto.UsersFoodDTO;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserDetailsImpl;
import ua.training.foodtracker.entity.UserFood;
import ua.training.foodtracker.repository.UserFoodRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFoodService {
    @Autowired
    private UserFoodRepository userFoodRepository;


    /*public Optional<Food> findByFoodName(String username){
        return userRepository.findByUsername(username);
    }*/

    @Transactional
    public UserFood save(UserFoodDTO userFoodDto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userFoodRepository.save(
                UserFood.builder()
                        .username(((UserDetailsImpl) principal).getUsername())
                        .foodname(userFoodDto.getFoodName())
                        .amount(userFoodDto.getAmount())
                        .date(Date.valueOf(LocalDate.now()))
                        .build()
        );
    }

    public UsersFoodDTO findAllTodays() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<UserFood> todaysFood = userFoodRepository
                .findByUsernameAndDate(((UserDetailsImpl) principal).getUsername(), Date.valueOf(LocalDate.now()));

        return UsersFoodDTO.builder().usersFood(todaysFood).build();


    }

    public UsersFoodDTO findAll() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return UsersFoodDTO.builder()
                .usersFood(
                        userFoodRepository.findByUsername(((UserDetailsImpl) principal).getUsername())
                                .stream()
                                .sorted(Comparator.comparing(UserFood::getDate, Comparator.reverseOrder()))
                                .collect(Collectors.toList())
                ).build();
    }

    public UsersFoodDTO getAllUsersFood() {

        return UsersFoodDTO.builder()
                .usersFood(
                        userFoodRepository.findAll()
                                .stream()
                                .sorted(Comparator.comparing(UserFood::getDate, Comparator.reverseOrder()))
                                .collect(Collectors.toList())
                ).build();
    }


}
