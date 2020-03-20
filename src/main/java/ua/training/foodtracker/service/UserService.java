package ua.training.foodtracker.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.foodtracker.config.SecurityConfiguration;
import ua.training.foodtracker.dto.UserDTO;
import ua.training.foodtracker.entity.User;
import ua.training.foodtracker.repository.UserRepository;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User save(UserDTO userDto){
        User user = new User();
        //user.setId(3L);
        user.setUsername(userDto.getUsername());
        user.setPassword(securityConfiguration.getPasswordEncoder().encode(userDto.getPassword()));
        user.setActive(true);
        user.setFirstName(userDto.getFirstName());
        user.setRoles("ROLE_USER");
        return userRepository.save(user);
    }



}
