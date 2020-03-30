package ua.training.foodtracker.dto;

import lombok.*;
import org.springframework.data.domain.PageImpl;
import ua.training.foodtracker.entity.UserFood;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MealsDTO {
    private PageImpl<MealDTO> meals;
}