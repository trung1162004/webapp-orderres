package doan._java_food.response.Cooperation;

import doan._java_food.response.User.UserRelationResponse;
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
public class CooperationResponse {
    private Integer id;

    private String name;

    private String avatar;

    private String status;

    private String description;

    private String phone;

    private String email;

    private Float price;

    private LocalDateTime deadline;

    private Integer service_type;

    private Date createdAt;
    private Date updatedAt;

    private UserRelationResponse user;
}
