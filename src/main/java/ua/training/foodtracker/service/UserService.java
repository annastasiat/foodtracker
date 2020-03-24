package ua.training.foodtracker.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.foodtracker.config.SecurityConfiguration;
import ua.training.foodtracker.dto.UserDTO;
import ua.training.foodtracker.entity.Food;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserDetailsImpl;
import ua.training.foodtracker.entity.UserFood;
import ua.training.foodtracker.repository.UserFoodRepository;
import ua.training.foodtracker.repository.UserRepository;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserFoodService userFoodService;
    @Autowired
    private FoodService foodService;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User save(UserDTO userDto) {

        return userRepository.save(
                User.builder()
                        .username(userDto.getUsername())
                        .active(true)
                        .password(securityConfiguration.getPasswordEncoder().encode(userDto.getPassword()))
                        .firstName(userDto.getFirstName())
                        .firstNameUa(userDto.getFirstNameUa())
                        .roles("ROLE_USER")
                        .height(userDto.getHeight())
                        .weight(userDto.getWeight())
                        .activityLevel(userDto.getActivityLevel())
                        .age(userDto.getAge())
                        .gender(userDto.getGender())
                        .build()
        );
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }




}
