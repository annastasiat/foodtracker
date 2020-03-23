package ua.training.foodtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.foodtracker.entity.Food;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserFood;

import java.sql.Date;
import java.util.List;

public interface UserFoodRepository   extends JpaRepository<UserFood, Long> {
    List<UserFood> findByUsernameAndDate(String username, Date date);
    List<UserFood> findByUsernameAndDateBetween(String username, Date date1, Date date2);
    List<UserFood> findByUsername(String username);
    List<UserFood> findAll();


    
}
