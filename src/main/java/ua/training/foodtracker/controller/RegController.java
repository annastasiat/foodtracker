package ua.training.foodtracker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.training.foodtracker.dto.UserDTO;
import ua.training.foodtracker.dto.UserFoodDTO;
import ua.training.foodtracker.entity.Food;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/registration/")
public class RegController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> register(UserDTO userDto) {

        Optional<User> user = userService.findByUsername(userDto.getUsername());
        if (user.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.save(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
