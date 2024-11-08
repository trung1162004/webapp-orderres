package doan._java_food.serviceApi.Comment;

import doan._java_food.requestDto.CategoryRequestDto;
import doan._java_food.requestDto.CommentRequestDto;
import doan._java_food.response.Category.CategoryResponse;
import doan._java_food.response.Category.ListCategoryResponse;
import doan._java_food.response.Comment.CommentResponse;
import doan._java_food.response.Comment.ListCommentResponse;

public interface CommentApiService {

    CommentResponse findById(Long id);
    ListCommentResponse findAndCount(int page, int page_size, String restaurant_id, String user_id, String status, String name, String email);
    CommentRequestDto save(CommentRequestDto data);
    CommentRequestDto update(Long id, CommentRequestDto data);
    void delete(Long data_id);

}
