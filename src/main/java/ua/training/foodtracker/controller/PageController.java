package ua.training.foodtracker.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.entity.UserDetailsImpl;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login(Model model) {
        return "login.html";
    }

    /*@RequestMapping("/")
    public String home() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("AUTH OK");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            System.out.println(((UserDetailsImpl) principal).getUsername());
        }
        return "user/index.html";
    }*/

    @RequestMapping("/registration")
    public String registration(){
        System.out.println("REG");
        return "registration.html";
    }

    @RequestMapping("/user")
    public String user() {
        return "user/index.html";
    }

    @RequestMapping("/admin")
    public String admin() {

        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        System.out.println(user.getRoles());*/
        return "admin/index.html";
    }
}
