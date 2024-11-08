package doan._java_food.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name =  "code")
    private String code;

    @Column(name =  "status")
    private String status;

    @Column(name =  "payment_status")
    private String paymentStatus;

    @Column(name =  "payment_type")
    private Integer paymentType;


    @Column(name = "type", columnDefinition = "VARCHAR(255) DEFAULT 'BOOKING'")
    private String type;

    @Column(columnDefinition = "TEXT",name =  "receiver_address")
    private String receiverAddress;

    @Column(name =  "receiver_email")
    private String receiverEmail;

    @Column(name =  "receiver_name")
    private String receiverName;

    @Column(name =  "receiver_phone")
    private String receiverPhone;

    @Column(columnDefinition = "TEXT", name =  "note")
    private String note;

    @Column(name = "total_price")
    private String totalPrice;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column( name =  "logo")
    private String logo;

    @Column(name = "price")
    private String price;

    @Column(name = "time_booking")
    private LocalDateTime timeBooking;

    @Column(name = "quantity")
    private Integer quantity;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "order_id")
//    private List<TransactionEntity> transactions;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }
}
