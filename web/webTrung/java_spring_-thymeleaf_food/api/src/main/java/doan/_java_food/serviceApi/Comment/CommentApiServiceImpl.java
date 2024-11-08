package doan._java_food.serviceApi.Comment;

import doan._java_food.models.*;
import doan._java_food.repository.*;
import doan._java_food.requestDto.CommentRequestDto;
import doan._java_food.requestDto.RestaurantRequestDto;
import doan._java_food.requestDto.UserRestaurantRequestDto;
import doan._java_food.response.Comment.CommentResponse;
import doan._java_food.response.Comment.ListCommentResponse;
import doan._java_food.response.MappingResponseDto;
import doan._java_food.response.Restaurant.ListRestaurantResponse;
import doan._java_food.response.Restaurant.RestaurantResponse;
import doan._java_food.serviceApi.Restaurant.RestaurantApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentApiServiceImpl implements CommentApiService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;



    @Autowired
    private MappingResponseDto responseDto;

    public Comment createOrUpdateData(CommentRequestDto dataRequest, Comment oldData) {
        Comment newData = oldData;
        if(oldData == null) {
            newData = new Comment();
            newData.setCreatedAt(new Date());
        }
        if(dataRequest.getName() != null) {
            newData.setName(dataRequest.getName());
        }
        if(dataRequest.getStatus() != null) {
            newData.setStatus(dataRequest.getStatus());
        }
        User user = newData.getUser();
        if(dataRequest.getUser_id() != null) {
            user = userRepository.getById(dataRequest.getUser_id());
            newData.setUser(user);
        }
        Restaurant r = newData.getRestaurant();
        if(dataRequest.getRestaurant_id() != null) {
            Optional<Restaurant> re = restaurantRepository.findById(dataRequest.getRestaurant_id());
            if(re.isEmpty()) {
                throw new RuntimeException("Not found restaurant");
            }
            newData.setRestaurant(re.get());
        }
        if(dataRequest.getVoteNumber() != null) {
            newData.setVoteNumber(dataRequest.getVoteNumber());
        }
        if(dataRequest.getContent() != null) {
            newData.setContent(dataRequest.getContent());
        }
        if(dataRequest.getPhone() != null) {
            newData.setPhone(dataRequest.getPhone());
        }
        if(dataRequest.getEmail() != null) {
            newData.setEmail(dataRequest.getEmail());
        }

        newData.setUpdatedAt(new Date());



        return newData;
    }
    @Override
    public CommentResponse findById(Long id) {
        return responseDto.getCommentResponse(repository.getById(id));

    }

    @Override
    public ListCommentResponse findAndCount(int page, int page_size, String restaurant_id, String user_id, String status, String name, String email) {
        List<Comment> results = this.repository.findAndCountBy(page * page_size, page_size,
                restaurant_id, user_id, status, name, email);

        Long total = this.repository.countByCondition(restaurant_id, user_id, status, name, email);
        if(total == null) {
            total = Long.valueOf(0);
        }

        ListCommentResponse dataListResponse = new ListCommentResponse();
        dataListResponse.setTotal(total);
        if(results.isEmpty()) {
            dataListResponse.setData(new ArrayList<>());
            return dataListResponse;
        }
        List<CommentResponse> data = new ArrayList<>();
        for (Comment item: results) {
            data.add(responseDto.getCommentResponse(item));
        }
        dataListResponse.setData(data);
        return dataListResponse;
    }



    @Override
    public CommentRequestDto save(CommentRequestDto data)  {
        Comment o = this.createOrUpdateData(data, null);
        Comment newRank = repository.save(o);
        return data;
    }



    @Override
    public CommentRequestDto update(Long id, CommentRequestDto data)  {
        var c = repository.getById(id);
        if(c != null) {
            Comment o = this.createOrUpdateData(data, c);
            Comment newRank = repository.save(o);

            return data;
        }
        return null;
    }

    @Override
    public void delete(Long data_id) {
        repository.deleteById(data_id);
    }
}
