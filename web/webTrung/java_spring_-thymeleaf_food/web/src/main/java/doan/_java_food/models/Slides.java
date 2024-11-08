package doan._java_food.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "slides")
@Getter
@Setter
public class Slides {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name =  "name")
    private String name;

    @Column(name =  "image")
    private String image;

    @Column(name =  "status")
    private String status;

}
