package ua.training.foodtracker.dto;

import lombok.*;
import ua.training.foodtracker.entity.UserFood;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MealDTO {
    String username;
    String foodName;
    Integer amount;
    LocalDate date;
    LocalTime time;


}
