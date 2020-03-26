package ua.training.foodtracker.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.training.foodtracker.dto.UserDTO;
import ua.training.foodtracker.dto.UserFoodDTO;
import ua.training.foodtracker.entity.Food;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.exception.UserExistsException;
import ua.training.foodtracker.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/registration/")
public class RegController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("register")
    public void register(UserDTO userDto) throws UserExistsException {

        Optional<User> user = userService.findByUsername(userDto.getUsername());
        if (user.isPresent()){
            log.info("UserExistsException throwing");
            throw new UserExistsException();
        }
        log.info("Saved user: {}", userService.save(userDto));
    }

}
