package doan._java_food.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "users_services")
@Getter
@Setter
public class UserService {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name =  "name")
    private String name;

    @Column(name =  "phone")
    private String phone;

    @Column(name =  "status", columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private String status;


    @Column(name =  "email")
    private String email;

    @Column(name =  "price")
    private Float price;


    @Column(name = "service_type")
    private Integer service_type;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(columnDefinition = "TEXT", name =  "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
