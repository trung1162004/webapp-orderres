package doan._java_food.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "blogs")
@Getter
@Setter
public class Blog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name =  "name")
    private String name;

    @Column(name =  "hot")
    private String hot;

    @Column(name =  "avatar")
    private String avatar;

    @Column(name =  "status")
    private String status;

    @Column(name =  "author")
    private String author;

    @Column(columnDefinition = "TEXT", name =  "description")
    private String description;



    @Column(columnDefinition = "TEXT", name =  "content")
    private String content;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

}
