package doan._java_food.response.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserResponse {
    private Integer id;
    private String name;
    private String status;
    private String email;
    private String gender;
    private String phone;
    private String avatar;
    private String type;
    private String address;
}
