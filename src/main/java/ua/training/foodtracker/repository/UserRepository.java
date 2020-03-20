package ua.training.foodtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.foodtracker.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
