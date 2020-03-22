package ua.training.foodtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserDetailsImpl;
import ua.training.foodtracker.repository.UserRepository;

import java.util.Optional;

@Service
public class UserCounting  {

    @Autowired
    private UserFoodService userFoodService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;


    public void countNorm() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userService.findByUsername(((UserDetailsImpl) principal).getUsername());
        //TODO

    }

    public int todaysCalories() {
        return userFoodService.findAllTodays()
                .stream()
                .mapToInt(food -> foodService.findByName(food.getFoodname()).get().getCalories())
                .reduce(Integer::sum).getAsInt();
    }

    public int todaysProteins() {
        return userFoodService.findAllTodays()
                .stream()
                .mapToInt(food -> foodService.findByName(food.getFoodname()).get().getProtein())
                .reduce(Integer::sum).getAsInt();
    }

    public int todaysCarbs() {
        return userFoodService.findAllTodays()
                .stream()
                .mapToInt(food -> foodService.findByName(food.getFoodname()).get().getCarbs())
                .reduce(Integer::sum).getAsInt();
    }

    public int todaysFats() {
        return userFoodService.findAllTodays()
                .stream()
                .mapToInt(food -> foodService.findByName(food.getFoodname()).get().getFat())
                .reduce(Integer::sum).getAsInt();
    }

}
