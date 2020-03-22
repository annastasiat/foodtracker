package ua.training.foodtracker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.training.foodtracker.config.LocaleConfiguration;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserDetailsImpl;
import ua.training.foodtracker.entity.UserFood;
import ua.training.foodtracker.service.FoodService;
import ua.training.foodtracker.service.UserFoodService;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class PageController {


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/account")
    public String account() {
        return "user/account";
    }


    @RequestMapping("/user")
    public String user() {
        return "user/index";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin/index";
    }
}
