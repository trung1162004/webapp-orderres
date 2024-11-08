package doan._java_food.serviceApi.Blog;

import doan._java_food.models.Blog;
import doan._java_food.repository.BlogRepository;
import doan._java_food.repository.UserRepository;
import doan._java_food.requestDto.BlogRequestDto;
import doan._java_food.response.Blog.BlogResponse;
import doan._java_food.response.Blog.ListBlogResponse;
import doan._java_food.response.MappingResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class BlogApiServiceImpl implements BlogApiService {

    @Autowired
    private BlogRepository repository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MappingResponseDto responseDto;

    public Blog createOrUpdateData(BlogRequestDto dataRequest, Blog oldData) {
        Blog newData = oldData;
        if(oldData == null) {
            newData = new Blog();
            newData.setCreatedAt(new Date());
        }
        if(dataRequest.getName() != null) {
            newData.setName(dataRequest.getName());
        }
        if(dataRequest.getStatus() != null) {
            newData.setStatus(dataRequest.getStatus());
        }

        if(dataRequest.getAvatar() != null) {
            newData.setAvatar(dataRequest.getAvatar());
        }
        if(dataRequest.getContent() != null) {
            newData.setContent(dataRequest.getContent());
        }
        if(dataRequest.getDescription() != null) {
            newData.setDescription(dataRequest.getDescription());
        }
        if(dataRequest.getAuthor() != null) {
            newData.setAuthor(dataRequest.getAuthor());
        }
        newData.setUpdatedAt(new Date());

        return newData;
    }
    @Override
    public BlogResponse findById(Long id) {
        return responseDto.getBlogInfo(repository.getById(id));

    }

    @Override
    public ListBlogResponse findAll(int page, int page_size) {
        Pageable pageable = PageRequest.of(page, page_size);
        Page<Blog> results = repository.findAll(pageable);

        ListBlogResponse dataListResponse = new ListBlogResponse();
        dataListResponse.setTotal(results.getTotalElements());
        if(results.isEmpty()) {
            dataListResponse.setData(new ArrayList<>());
            return dataListResponse;
        }
        List<BlogResponse> data = new ArrayList<>();
        for (Blog item: results) {
            data.add(responseDto.getBlogInfo(item));
        }
        dataListResponse.setData(data);
        return dataListResponse;
    }

    @Override
    public BlogRequestDto save(BlogRequestDto data) {
        Blog o = this.createOrUpdateData(data, null);
        Blog newRank = repository.save(o);
        return data;
    }

    @Override
    public BlogRequestDto update(Long id, BlogRequestDto data) {
        var c = repository.getById(id);
        if(c != null) {
            Blog o = this.createOrUpdateData(data, c);
            Blog newRank = repository.save(o);
            return data;
        }
        return null;
    }

    @Override
    public void delete(Long data_id) {
        repository.deleteById(data_id);
    }
}
