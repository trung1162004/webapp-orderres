package doan._java_food.serviceApi.Category;

import doan._java_food.models.Category;
import doan._java_food.repository.CategoryRepository;
import doan._java_food.requestDto.CategoryRequestDto;
import doan._java_food.response.Category.CategoryResponse;
import doan._java_food.response.Category.ListCategoryResponse;
import doan._java_food.response.MappingResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryApiServiceImpl implements CategoryApiService {

    @Autowired
    private CategoryRepository repository;

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));



    @Autowired
    private MappingResponseDto responseDto;

    public Category createOrUpdateData(CategoryRequestDto dataRequest, Category oldData) {
        Category newData = oldData;
        if(oldData == null) {
            newData = new Category();
        }
        if(dataRequest.getName() != null) {
            newData.setName(dataRequest.getName());
        }
        if(dataRequest.getStatus() != null) {
            newData.setStatus(dataRequest.getStatus());
        }
        if(dataRequest.getImage() != null) {
            newData.setImage(dataRequest.getImage());

        }
        return newData;
    }
    @Override
    public CategoryResponse findById(Long id) {
        return responseDto.getCategoryInfo(repository.getById(id));

    }

    @Override
    public ListCategoryResponse findAll(int page, int page_size) {
        Pageable pageable = PageRequest.of(page, page_size);
        Page<Category> results = repository.findAll(pageable);
        ListCategoryResponse dataListResponse = new ListCategoryResponse();
        dataListResponse.setTotal(results.getTotalElements());
        if(results.isEmpty()) {
            dataListResponse.setData(new ArrayList<>());
            return dataListResponse;
        }
        List<CategoryResponse> data = new ArrayList<>();
        for (Category item: results) {
            data.add(responseDto.getCategoryInfo(item));
        }
        dataListResponse.setData(data);
        return dataListResponse;
    }

    @Override
    public CategoryRequestDto save(CategoryRequestDto data) {
        Category o = this.createOrUpdateData(data, null);
        Category newRank = repository.save(o);
        return data;
    }

    @Override
    public CategoryRequestDto update(Long id, CategoryRequestDto data) {
        var c = repository.getById(id);
        if(c != null) {
            Category o = this.createOrUpdateData(data, c);
            Category newRank = repository.save(o);
            return data;
        }
        return null;
    }

    @Override
    public void delete(Long data_id) {
        repository.deleteById(data_id);
    }
}
