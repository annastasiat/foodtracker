package ua.training.foodtracker.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsersMealStatDTO {
    private List<UserMealStatDTO> usersFood;
}
