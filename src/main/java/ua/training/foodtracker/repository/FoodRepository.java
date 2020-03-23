package ua.training.foodtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.foodtracker.entity.Food;
import ua.training.foodtracker.entity.User;

import java.util.List;
import java.util.Optional;

public interface FoodRepository  extends JpaRepository<Food, Long> {
    Optional<Food> findByName(String name);
    List<Food> findAll();
}
