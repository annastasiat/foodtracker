package ua.training.foodtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.foodtracker.config.SecurityConfiguration;
import ua.training.foodtracker.dto.UserFoodDTO;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserDetailsImpl;
import ua.training.foodtracker.entity.UserFood;
import ua.training.foodtracker.repository.UserFoodRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class UserFoodService {
    @Autowired
    private UserFoodRepository userFoodRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    /*public Optional<Food> findByFoodName(String username){
        return userRepository.findByUsername(username);
    }*/

    @Transactional
    public UserFood save(UserFoodDTO userFoodDto) {
        UserFood userFood = new UserFood();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        userFood.setUsername(((UserDetailsImpl) principal).getUsername());
        userFood.setFoodname(userFoodDto.getFoodName());
        userFood.setAmount(userFoodDto.getAmount());
        userFood.setDate(Date.valueOf(LocalDate.now()));

        return userFoodRepository.save(userFood);
    }

    public List<UserFood> findAllTodays() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userFoodRepository.findByUsernameAndDate(((UserDetailsImpl) principal).getUsername(), Date.valueOf(LocalDate.now()));
    }

    public List<UserFood> findAll() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userFoodRepository.findByUsername(((UserDetailsImpl) principal).getUsername());
    }

    public List<UserFood> getAllUsersFood(){
        return userFoodRepository.findAll();
    }


}
