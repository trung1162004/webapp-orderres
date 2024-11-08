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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CooperationRequestDto {

    private String name;

    private String status;

    private String description;

    private String phone;

    private String email;

    private Float price;

    private Integer service_type;

    private Long user_id;
}
