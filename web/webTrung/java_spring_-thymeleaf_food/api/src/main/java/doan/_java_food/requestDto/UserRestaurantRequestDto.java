package doan._java_food.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserRestaurantRequestDto {
    private Integer user_id;
    private Integer restaurant_id;
    private String type;
}
