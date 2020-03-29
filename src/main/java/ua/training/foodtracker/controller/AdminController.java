package ua.training.foodtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.training.foodtracker.dto.FoodDTO;
import ua.training.foodtracker.dto.FoodsDTO;
import ua.training.foodtracker.dto.UsersDTO;
import ua.training.foodtracker.dto.UsersFoodDTO;
import ua.training.foodtracker.entity.Food;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserFood;
import ua.training.foodtracker.service.FoodService;
import ua.training.foodtracker.service.UserFoodService;
import ua.training.foodtracker.service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserFoodService userFoodService;

    @GetMapping("all_users")
    public UsersDTO getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("all_food")
    public FoodsDTO getAllFood() {
        return foodService.getAllFood();
    }

    @GetMapping("all_users_food")
    public UsersFoodDTO getAllUsersFood() {
        return userFoodService.getAllUsersFood();
    }


}
