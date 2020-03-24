package ua.training.foodtracker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.training.foodtracker.dto.*;
import ua.training.foodtracker.entity.Food;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserDetailsImpl;
import ua.training.foodtracker.entity.UserFood;
import ua.training.foodtracker.service.FoodService;
import ua.training.foodtracker.service.UserCounting;
import ua.training.foodtracker.service.UserFoodService;
import ua.training.foodtracker.service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserFoodService userFoodService;
    @Autowired
    private UserCounting userCounting;
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;

    @GetMapping("todays_food")
    public UsersFoodDTO getTodaysFood() {
        List<UserFood> todaysFood = userFoodService.findAllTodays();
        return UsersFoodDTO.builder().usersFood(todaysFood).build();
    }

    @GetMapping("all_food")
    public UsersFoodDTO getAllFood() {
        List<UserFood> allFood = userFoodService.findAll().stream()
                .sorted(Comparator.comparing(UserFood::getDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return UsersFoodDTO.builder().usersFood(allFood).build();
    }

    @GetMapping("user_statistics")
    public UserTodayStatisticsDTO getUserStatistics() {
        return UserTodayStatisticsDTO
                .builder()
                .calories(userCounting.todaysCalories())
                .carbs(userCounting.todaysCarbs())
                .protein(userCounting.todaysProteins())
                .fat(userCounting.todaysFats())
                .build();
    }

    @PostMapping("add_user_food")
    public ResponseEntity<String> addUserFood(UserFoodDTO userFoodDTO) {

        Optional<Food> food = foodService.findByName(userFoodDTO.getFoodName());

        if (!food.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userFoodService.save(userFoodDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("add_food")
    public ResponseEntity<String> addFood(FoodDTO foodDTO) {

        Optional<Food> food = foodService.findByName(foodDTO.getName());

        if (food.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        foodService.save(foodDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("change_account")
    public ResponseEntity<String> addFood(UserDTO userDTO) {

        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> userOp = userService.findByUsername(principal.getUsername());
        if (!userOp.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //TODO record update in repository and service
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
