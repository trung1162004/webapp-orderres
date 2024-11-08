package doan._java_food.requestDto;

import doan._java_food.models.Restaurant;
import doan._java_food.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CommentRequestDto {

    private String status;
    private String email;
    private String name;
    private Integer voteNumber;
    private String phone;
    private String content;
    private Long user_id;
    private Long restaurant_id ;
}
