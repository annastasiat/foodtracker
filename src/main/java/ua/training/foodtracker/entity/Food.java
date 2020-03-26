package ua.training.foodtracker.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "carbs", nullable = false)
    private Integer carbs;

    @Column(name = "protein", nullable = false)
    private Integer protein;

    @Column(name = "fat", nullable = false)
    private Integer fat;

    @Column(name = "calories")
    private Integer calories;
}
