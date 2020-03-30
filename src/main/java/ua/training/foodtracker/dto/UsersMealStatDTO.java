package ua.training.foodtracker.dto;

import lombok.*;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsersMealStatDTO {
    private PageImpl<UserMealStatDTO> usersFood;
}
