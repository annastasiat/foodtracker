package ua.training.foodtracker.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "users_food")
public class UserFood {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "foodname", nullable = false)
    private String foodname;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "date", nullable = false)
    private Date date;


}
