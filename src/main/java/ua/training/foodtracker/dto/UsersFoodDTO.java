package ua.training.foodtracker.dto;


import lombok.*;
import ua.training.foodtracker.entity.UserFood;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsersFoodDTO {
    private List<UserFood> usersFood;
}
