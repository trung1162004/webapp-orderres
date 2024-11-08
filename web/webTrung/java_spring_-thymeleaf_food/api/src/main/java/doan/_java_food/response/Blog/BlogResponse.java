package doan._java_food.response.Blog;

import doan._java_food.response.User.UserRelationResponse;
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
public class BlogResponse {
    private Integer id;
    private String name;

    private String avatar;

    private String status;

    private String author;

    private String description;

    private String content;

    private Date createdAt;
    private Date updatedAt;
}
