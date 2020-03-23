package ua.training.foodtracker.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserRegDTO {

    private String username;
    private String password;
    private String firstName;
    private Integer height;
    private Integer weight;
    private Integer activityLevel;
    private Integer age;
    private String firstNameUa;


}
