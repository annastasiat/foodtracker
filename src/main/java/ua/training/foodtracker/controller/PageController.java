package ua.training.foodtracker.controller;


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

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String reg() {
        return "registration";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin/index";
    }

    @RequestMapping("/account")
    public String account(Model model) {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("principal", principal);
        model.addAttribute("isAdmin", principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        return "user/account";
    }

    @GetMapping("/account/change")
    public String accountChange() {
        return "user/account_change";
    }

    @GetMapping(value = {"/statistics"})
    public String personList(Model model) {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("isAdmin", principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        return "user/statistics";
    }

    @GetMapping("/")
    public String home(Model model) {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("isAdmin", principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        return "user/index";
    }





}
