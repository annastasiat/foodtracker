package ua.training.foodtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.training.foodtracker.dto.*;
import ua.training.foodtracker.service.FoodService;
import ua.training.foodtracker.service.UserFoodService;
import ua.training.foodtracker.service.UserService;

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
        return userService.findAll();
    }

    @GetMapping("all_food")
    public FoodsDTO getAllFood() {
        return foodService.findAll();
    }

    @GetMapping("all_users_food")
    public MealsDTO getAllUsersFood() {
        return userFoodService.getAllUsersFoodForAdmin();
    }


}
