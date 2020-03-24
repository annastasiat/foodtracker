package ua.training.foodtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.training.foodtracker.entity.ActivityLevel;
import ua.training.foodtracker.entity.Gender;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserDetailsImpl;
import ua.training.foodtracker.repository.UserRepository;

import java.util.Optional;

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
        Optional<User> userOp = userService.findByUsername(((UserDetailsImpl) principal).getUsername());
        if (userOp.isPresent()) {
            User user = userOp.get();
            return (int) (ActivityLevel.valueOf(user.getActivityLevel()).getValue()
                    * (Gender.valueOf(user.getGender()).getValue()
                        + 10 * user.getWeight()
                        + 6.25 * user.getHeight()
                        - 5 * user.getAge()));
        }
        return -1;

    }

    public int todaysCalories() {
        return userFoodService.findAllTodays()
                .stream()
                .map(food -> foodService.findByName(food.getFoodname()))
                .filter(Optional::isPresent)
                .mapToInt(food -> food.get().getCalories())
                .reduce(Integer::sum).orElse(0);
    }

    public int todaysProteins() {
        return userFoodService.findAllTodays()
                .stream()
                .mapToInt(food -> foodService.findByName(food.getFoodname()).get().getProtein())
                .reduce(Integer::sum).orElse(0);
    }

    public int todaysCarbs() {
        return userFoodService.findAllTodays()
                .stream()
                .mapToInt(food -> foodService.findByName(food.getFoodname()).get().getCarbs())
                .reduce(Integer::sum).orElse(0);
    }

    public int todaysFats() {
        return userFoodService.findAllTodays()
                .stream()
                .mapToInt(food -> foodService.findByName(food.getFoodname()).get().getFat())
                .reduce(Integer::sum).orElse(0);
    }

}
