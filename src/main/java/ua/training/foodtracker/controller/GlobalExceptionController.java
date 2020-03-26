package ua.training.foodtracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.training.foodtracker.exception.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserExistsException.class, UserNotExistsException.class,
            FoodExistsException.class, FoodNotExistsException.class, PasswordIncorrectException.class})
    public  void userEx() {
        log.info("exception handled");
    }
}
