package doan._java_food.requestDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BlogRequestDto {

    private String name;

    private String avatar;

    private String status;

    private String author;

    private String description;

    private String content;
}
