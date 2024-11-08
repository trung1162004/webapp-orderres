package doan._java_food.service.Blog;

import doan._java_food.models.Blog;
import doan._java_food.models.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {

    List<Blog> getAll();

    List<Blog> findAndCount(int page, int page_size, String status, String name,
                            String author, String hot);

    Integer countByCondition(String status, String name,
                             String author, String hot);

    Boolean create(Blog data, MultipartFile fileAvatar);
    Blog findById(Long id);

    Boolean update(Blog data, MultipartFile fileAvatar);

    Boolean delete(Long id);

}
