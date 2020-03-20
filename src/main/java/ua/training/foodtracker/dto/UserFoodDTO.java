package ua.training.foodtracker.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserFoodDTO {
    String foodName;
    Integer amount;
}
