package ua.training.foodtracker.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.foodtracker.config.LocaleConfiguration;
import ua.training.foodtracker.dto.UserTodayStatisticsDTO;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserDetailsImpl;
import ua.training.foodtracker.entity.UserFood;
import ua.training.foodtracker.service.FoodService;
import ua.training.foodtracker.service.UserCounting;
import ua.training.foodtracker.service.UserFoodService;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class PageController {

   @Autowired
   private UserCounting userCounting;

    @ModelAttribute("isAdmin")
    public boolean isAdmin() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @ModelAttribute("principal")
    public UserDetailsImpl principal() {
         return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @ModelAttribute("todaysCalories")
    public int todaysCalories() {
        log.info("todaysCalories");
        return userCounting.todaysCalories();
    }

    @ModelAttribute("caloriesNorm")
    public int caloriesNorm() {
        log.info("caloriesNorm");
        return userCounting.countNorm();
    }


    @GetMapping("/admin")
    public String admin() {
        log.info("admin page");
        return "admin/index";
    }

    @RequestMapping("/account")
    public String account() {
        log.info("account page");
        return "user/account";
    }

    @GetMapping("/account/change")
    public String accountChange() {
        log.info("account change page");
        return "user/account_change";
    }
    @GetMapping("/account/change/password")
    public String accountChangePassword() {
        log.info("account change password page");
        return "user/password_change";
    }

    @GetMapping(value = {"/statistics"})
    public String personList() {
        log.info("statistics page");
        return "user/statistics";
    }

    @GetMapping("/")
    public String home() {
        log.info("home page");
        return "user/index";
    }





}
