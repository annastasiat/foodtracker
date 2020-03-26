package ua.training.foodtracker.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.foodtracker.config.SecurityConfiguration;
import ua.training.foodtracker.dto.*;
import ua.training.foodtracker.entity.Food;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserDetailsImpl;
import ua.training.foodtracker.entity.UserFood;
import ua.training.foodtracker.exception.FoodExistsException;
import ua.training.foodtracker.exception.FoodNotExistsException;
import ua.training.foodtracker.exception.PasswordIncorrectException;
import ua.training.foodtracker.exception.UserNotExistsException;
import ua.training.foodtracker.service.FoodService;
import ua.training.foodtracker.service.UserCounting;
import ua.training.foodtracker.service.UserFoodService;
import ua.training.foodtracker.service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
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
    @Autowired
    private SecurityConfiguration securityConfiguration;

    @GetMapping("user")
    public UserDTO user() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOp = userService.findByUsername(principal.getUsername());
        //log.info("{}", userOp.get());
        return new UserDTO(userOp.get());
    }

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
                .caloriesNorm(userCounting.countNorm())
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("add_user_food")
    public void addUserFood(UserFoodDTO userFoodDTO) throws FoodNotExistsException {
        Optional<Food> food = foodService.findByName(userFoodDTO.getFoodName());
        if (!food.isPresent()) {
            throw new FoodNotExistsException();
        }
        log.info("User food added: {}", userFoodService.save(userFoodDTO));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("add_food")
    public void addFood(FoodDTO foodDTO) throws FoodExistsException {

        Optional<Food> food = foodService.findByName(foodDTO.getName());
        if (food.isPresent()) {
            throw new FoodExistsException();
        }

        log.info("Food added: {}", foodService.save(foodDTO));
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("change_password")
    public void changePassword(PasswordChangeDTO passwordChangeDTO) throws UserNotExistsException, PasswordIncorrectException {

        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("PasswordChangeDTO: {}", passwordChangeDTO);

        Optional<User> userOp = userService.findByUsername(principal.getUsername());

        if (!userOp.isPresent()) {
            throw new UserNotExistsException();
        }
        if (!securityConfiguration.getPasswordEncoder()
                .matches(passwordChangeDTO.getOldPassword(), principal.getPassword())) {
            throw new PasswordIncorrectException();
        }

        userService.updatePassword(securityConfiguration.getPasswordEncoder()
                .encode(passwordChangeDTO.getNewPassword()), principal.getUsername());
        log.info("Password changed");
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("change_account")
    public void changeAccount(UserDTO userDTO) {

        //TODO record update in repository and service
    }
}

