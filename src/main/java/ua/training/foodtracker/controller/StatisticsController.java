package ua.training.foodtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.training.foodtracker.dto.UserTodayStatisticsDTO;
import ua.training.foodtracker.entity.UserDetailsImpl;
import ua.training.foodtracker.entity.UserFood;
import ua.training.foodtracker.service.FoodService;
import ua.training.foodtracker.service.UserCounting;
import ua.training.foodtracker.service.UserFoodService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StatisticsController {

    @Autowired
    private UserFoodService userFoodService;
    @Autowired
    private UserCounting userCounting;

    @ModelAttribute("isAdmin")
    public boolean isAdmin() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }


    @GetMapping(value = {"/statistics"})
    public String personList(Model model) {

        List<UserFood> todaysFood = userFoodService.findAllTodays();

        List<UserFood> allFood = userFoodService.findAll().stream()
                .sorted(Comparator.comparing(UserFood::getDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        UserTodayStatisticsDTO userStatistics = UserTodayStatisticsDTO.builder()
                .calories(userCounting.todaysCalories())
                .carbs(userCounting.todaysCarbs())
                .protein(userCounting.todaysProteins())
                .fat(userCounting.todaysFats()).build();



        model.addAttribute("todaysFood", todaysFood);
        model.addAttribute("allFood", allFood);
        model.addAttribute("userStatistics", userStatistics);


        return "user/statistics";
    }
}
