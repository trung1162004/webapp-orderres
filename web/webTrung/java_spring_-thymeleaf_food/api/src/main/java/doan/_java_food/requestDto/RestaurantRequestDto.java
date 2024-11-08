package doan._java_food.requestDto;

import doan._java_food.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RestaurantRequestDto {

    private String name;

    private String logo;

    private String status;
    private String hot;
    private String hot_discount;

    private String email;

    private String type;

    private String start_date;

    private String end_date;

    private String workTimeClose;
    private String workTimeOpen;

    private Integer quantity;

    private String price;

    private String description;

    private String address;

    private String content;

    private String phone;

    private Integer user_id;

    private List<Integer> category_ids;

    private String depositRegulation;
    private String discountRegulation;
    private String pasgoTimeRegulation;
    private String reservationTimeRegulation;
    private String holdTimeRegulation;
    private String minimumGuestsRegulation;
    private String invoiceRegulation;
    private String serviceFeeRegulation;
    private String bringInFeeRegulation;
    
    private String fit;
    private String specialDish;
    private String space;
    private String parking;
    private String feature;

    private Set<String> utilities;
}
