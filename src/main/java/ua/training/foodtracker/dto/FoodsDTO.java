package ua.training.foodtracker.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import ua.training.foodtracker.entity.Food;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FoodsDTO {
    private Page<Food> foods;
}
