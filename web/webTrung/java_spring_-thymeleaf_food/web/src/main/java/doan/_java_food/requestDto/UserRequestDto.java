package doan._java_food.requestDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserRequestDto {

    private String name;

    private String email;

    private String phone;

    private String password;

    private String username;

    private String gender;

    private String address;

    private String avatar;

    private String type;

    private String status;
}
