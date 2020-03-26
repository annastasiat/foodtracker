package ua.training.foodtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ua.training.foodtracker.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class FoodtrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodtrackerApplication.class, args);
    }

}
