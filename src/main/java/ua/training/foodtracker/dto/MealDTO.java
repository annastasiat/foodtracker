package ua.training.foodtracker.dto;

import lombok.*;
import ua.training.foodtracker.entity.UserFood;

import java.util.Date;

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
    Date date;

    public MealDTO(UserFood userFood){
        this.username = userFood.getUser().getUsername();
        this.foodName = userFood.getFood().getName();
        this.amount = userFood.getAmount();
        this.date = userFood.getDate();
    }
}
