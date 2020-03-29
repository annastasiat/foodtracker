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
public class UserMealStatDTO {

    String foodName;
    Integer amount;
    Date date;

    public UserMealStatDTO(UserFood userFood) {
        this.foodName = userFood.getFood().getName();
        this.amount = userFood.getAmount();
        this.date = userFood.getDate();
    }
}
