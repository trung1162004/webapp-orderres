package doan._java_food.response.Order;

import doan._java_food.response.Category.CategoryResponse;
import doan._java_food.response.User.UserRelationResponse;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderResponse {
    private Integer id;

    private String code;

    private String status;

    private String type;

    private String receiverAddress;

    private String receiverEmail;

    private String receiverName;

    private String receiverPhone;

    private String note;

    private Float totalPrice;

    private Float discount;

    private String restaurantName;

    private String logo;

    private Float price;

    private LocalDateTime timeBooking;

    private Integer quantity;

    private Date createdAt;
    private Date updatedAt;

    private UserRelationResponse user;
}
