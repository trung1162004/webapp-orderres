package doan._java_food.response.Restauran;

import doan._java_food.response.User.UserRelationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

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
    private List<String> images;
    private Float discount;

    private String content;

    private String phone;
    private Date createdAt;
    private Date updatedAt;

    private UserRelationResponse user;
}
