package doan._java_food.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name =  "status", columnDefinition = "VARCHAR(255) DEFAULT 'APPROVED'")
    private String status;

    @Column(name =  "email")
    private String email;

    @Column(name =  "name")
    private String name;

    @Column(name =  "vote_number")
    private Integer voteNumber;

    @Column(name =  "phone")
    private String phone;

    @Column(columnDefinition = "TEXT", name =  "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id",referencedColumnName = "id")
    private Restaurant restaurant;


    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
