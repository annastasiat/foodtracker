package ua.training.foodtracker.dto;

import lombok.*;

import javax.persistence.Column;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FoodDTO {

    private String name;
    private Integer carbs;
    private Integer protein;
    private Integer fat;
    private Integer calories;
}
