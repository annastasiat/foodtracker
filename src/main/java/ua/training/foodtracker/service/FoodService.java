package ua.training.foodtracker.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.foodtracker.dto.FoodDTO;
import ua.training.foodtracker.dto.UserDTO;
import ua.training.foodtracker.entity.Food;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.repository.FoodRepository;
import ua.training.foodtracker.repository.UserFoodRepository;

import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public Optional<Food> findByName(String name){
        return foodRepository.findByName(name);
    }

    @Transactional
    public Food save(FoodDTO foodDTO){
        Food food = new Food();
        food.setName(foodDTO.getName());
        food.setCarbs(foodDTO.getCarbs());
        food.setProtein(foodDTO.getProtein());
        food.setFat(foodDTO.getFat());
        food.setCalories(foodDTO.getCalories());

        return foodRepository.save(food);
    }


}
