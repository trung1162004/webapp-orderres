package doan._java_food.response.Comment;

import doan._java_food.response.Cooperation.CooperationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ListCommentResponse {
    private Long total;
    private List<CommentResponse> data;
}
