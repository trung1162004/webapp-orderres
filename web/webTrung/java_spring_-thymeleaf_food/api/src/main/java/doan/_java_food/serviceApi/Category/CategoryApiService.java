package doan._java_food.serviceApi.Category;

import doan._java_food.requestDto.CategoryRequestDto;
import doan._java_food.response.Category.CategoryResponse;
import doan._java_food.response.Category.ListCategoryResponse;

public interface CategoryApiService {

    CategoryResponse findById(Long id);
    ListCategoryResponse findAll(int page, int page_size);
    CategoryRequestDto save(CategoryRequestDto data);
    CategoryRequestDto update(Long id, CategoryRequestDto data);
    void delete(Long data_id);

}
