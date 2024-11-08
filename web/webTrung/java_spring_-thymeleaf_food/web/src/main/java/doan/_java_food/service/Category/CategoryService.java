package doan._java_food.service.Category;

import doan._java_food.models.Category;
import doan._java_food.models.Slides;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Boolean create(Category data, MultipartFile fileAvatar);
    Category findById(Long id);

    Boolean update(Category data, MultipartFile fileAvatar);

    Boolean delete(Long id);

}
