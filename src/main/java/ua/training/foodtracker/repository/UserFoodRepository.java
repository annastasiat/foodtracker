package ua.training.foodtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.foodtracker.entity.Food;
import ua.training.foodtracker.entity.UserFood;

public interface UserFoodRepository   extends JpaRepository<UserFood, Long> {
    
}
