package ua.training.foodtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.foodtracker.entity.Food;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserFood;

import java.sql.Date;
import java.util.List;

public interface UserFoodRepository extends JpaRepository<UserFood, Long> {
    List<UserFood> findByUser_UsernameAndDate(String username, Date date);

    List<UserFood> findByUser_Username(String username);

    //List<UserFood> findByUser(User user);

    List<UserFood> findAll();


}
