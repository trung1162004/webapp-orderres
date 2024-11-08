package doan._java_food.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
//@Table(name = "transactions")
@Getter
@Setter
public class TransactionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "restaurant_name")
    private String productName;

    @Column( name =  "image")
    private String image;


    @Column(name = "price")
    private Float price;


    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private Float totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Order order;



}
