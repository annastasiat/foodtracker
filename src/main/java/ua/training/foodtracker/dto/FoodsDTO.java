package ua.training.foodtracker.dto;

import lombok.*;
import ua.training.foodtracker.entity.Food;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FoodsDTO {
    private List<Food> foods;
}
