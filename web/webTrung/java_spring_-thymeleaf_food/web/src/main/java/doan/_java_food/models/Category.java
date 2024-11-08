package doan._java_food.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name =  "name")
    private String name;

    @Column(name =  "image")
    private String image;

    private Boolean checked;

    @Column(name =  "status")
    private String status;

}
