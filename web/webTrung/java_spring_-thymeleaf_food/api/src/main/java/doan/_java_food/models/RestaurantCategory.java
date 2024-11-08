package doan._java_food.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "restaurant_categories")
@Getter
@Setter
public class RestaurantCategory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
//    @MapsId("id")
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    @ManyToOne
//    @MapsId("id")
    @JoinColumn(name = "category_id")
    Category category;

}
