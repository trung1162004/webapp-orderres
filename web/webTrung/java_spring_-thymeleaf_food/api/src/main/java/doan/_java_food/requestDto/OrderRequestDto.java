package doan._java_food.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderRequestDto {

    private String code;

    private String status;

    private String type;

    private String receiverAddress;

    private String receiverEmail;

    private String receiverName;

    private String receiverPhone;

    private String note;

    private String totalPrice;

    private Float discount;

    private Integer userId;

    private String restaurantName;

    private Integer restaurantId;

    private String logo;

    private String price;

    private String timeBooking;

    private Integer quantity;

}
