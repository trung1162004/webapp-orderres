package doan._java_food.serviceApi.Blog;

import doan._java_food.requestDto.BlogRequestDto;
import doan._java_food.response.Blog.BlogResponse;
import doan._java_food.response.Blog.ListBlogResponse;

public interface BlogApiService {

    BlogResponse findById(Long id);
    ListBlogResponse findAll(int page, int page_size);
    BlogRequestDto save(BlogRequestDto data);
    BlogRequestDto update(Long id, BlogRequestDto data);
    void delete(Long data_id);

}
