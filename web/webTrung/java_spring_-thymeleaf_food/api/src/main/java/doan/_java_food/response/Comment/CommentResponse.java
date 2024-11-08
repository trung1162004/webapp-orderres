package doan._java_food.response.Comment;

import doan._java_food.models.Restaurant;
import doan._java_food.models.User;
import doan._java_food.response.Restaurant.RestaurantResponse;
import doan._java_food.response.User.UserRelationResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CommentResponse {

    private Integer id;

    private String status;

    private String email;

    private String name;

    private Integer voteNumber;

    private String phone;

    private String content;


    private Date createdAt;
    private Date updatedAt;

    private UserRelationResponse user;
    private RestaurantResponse restaurant;

}
