package doan._java_food.response.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserRelationResponse {
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String avatar;
}
