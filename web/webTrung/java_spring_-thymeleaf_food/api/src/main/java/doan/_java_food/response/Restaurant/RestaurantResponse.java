package doan._java_food.response.Restaurant;

import doan._java_food.models.Location;
import doan._java_food.response.Category.CategoryResponse;
import doan._java_food.response.User.UserRelationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RestaurantResponse {
    private Integer id;
    private String name;

    private String logo;

    private String status;

    private String email;

    private String description;

    private String address;

    private String content;

    private String phone;
    private String type;

    private List<String> images;
    private Float discount;

    private String start_date;

    private String end_date;

    private String workTimeClose;
    private String workTimeOpen;

    private Integer quantity;

    private String price;

    private Date createdAt;
    private Date updatedAt;

    private UserRelationResponse user;
    private List<CategoryResponse> categories;
    
    private Set<String> utilities;
    private String fit;
    private String specialDish;
    private String space;
    private String parking;
    private String feature;

    private String depositRegulation;
    private String discountRegulation;
    private String pasgoTimeRegulation;
    private String reservationTimeRegulation;
    private String holdTimeRegulation;
    private String minimumGuestsRegulation;
    private String invoiceRegulation;
    private String serviceFeeRegulation;
    private String bringInFeeRegulation;
    
    
}
