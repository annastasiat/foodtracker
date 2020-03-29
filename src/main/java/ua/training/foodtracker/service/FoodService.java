package ua.training.foodtracker.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.foodtracker.dto.FoodDTO;
import ua.training.foodtracker.dto.FoodsDTO;
import ua.training.foodtracker.entity.Food;
import ua.training.foodtracker.repository.FoodRepository;

import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public Optional<Food> findByName(String name) {
        return foodRepository.findByName(name);
    }

    @Transactional
    public Food save(FoodDTO foodDTO) {
        return foodRepository.save(Food.builder()
                .name(foodDTO.getName())
                .carbs(foodDTO.getCarbs())
                .protein(foodDTO.getProtein())
                .fat(foodDTO.getFat())
                .calories(foodDTO.getCalories())
                .build());
    }

    public FoodsDTO findAll() {
        return FoodsDTO.builder().foods(foodRepository.findAll()).build();
    }


}
