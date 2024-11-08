package doan._java_food.response;


import doan._java_food.models.*;
import doan._java_food.requestDto.CommentRequestDto;
import doan._java_food.response.Blog.BlogResponse;
import doan._java_food.response.Category.CategoryResponse;
import doan._java_food.response.Comment.CommentResponse;
import doan._java_food.response.Cooperation.CooperationResponse;
import doan._java_food.response.Order.OrderResponse;
import doan._java_food.response.Restaurant.RestaurantResponse;
import doan._java_food.response.Slide.SlideResponse;
import doan._java_food.response.User.UserRelationResponse;
import doan._java_food.response.User.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingResponseDto {

    public UserResponse getUserInfo(User data) {
        UserResponse newData = new UserResponse();
        BeanUtils.copyProperties(data, newData);
        return newData;
    }

    public CategoryResponse getCategoryInfo(Category data) {
        if(data != null) {
            CategoryResponse newData = new CategoryResponse();
            BeanUtils.copyProperties(data, newData);
            return newData;
        }
        return null;
    }
    public SlideResponse getSlideInfo(Slides data) {
        if(data != null) {
            SlideResponse newData = new SlideResponse();
            BeanUtils.copyProperties(data, newData);
            return newData;
        }
        return null;
    }
    public RestaurantResponse getRestaurantInfo(Restaurant data) {
        if(data == null) {
            return null;
        }
        RestaurantResponse newData = new RestaurantResponse();
        BeanUtils.copyProperties(data, newData);
        if(data.getUser() != null)  {
            newData.setUser(new UserRelationResponse(data.getUser().getId(),
                    data.getUser().getName(), data.getUser().getEmail(),
                    data.getUser().getGender(),
                    data.getUser().getAvatar()));
        }
        List<CategoryResponse> categories = new ArrayList<>();
        if(data.getCategories() != null) {
            for (RestaurantCategory item: data.getCategories()) {
                categories.add(getCategoryInfo(item.getCategory()));
            }
        }
        newData.setCategories(categories);
        return newData;
    }

    public CooperationResponse getCooperation(UserService data) {
        if(data == null) {
            return null;
        }
        CooperationResponse newData = new CooperationResponse();
        BeanUtils.copyProperties(data, newData);
        if(data.getUser() != null)  {
            newData.setUser(new UserRelationResponse(data.getUser().getId(),
                    data.getUser().getName(), data.getUser().getEmail(),
                    data.getUser().getGender(),
                    data.getUser().getAvatar()));
        }
        return newData;
    }

    public BlogResponse getBlogInfo(Blog data) {
        if(data == null) {
            return null;
        }
        BlogResponse newData = new BlogResponse();
        BeanUtils.copyProperties(data, newData);
        return newData;
    }

    public OrderResponse getOrderResponse(Order data) {
        if(data == null) {
            return null;
        }
        OrderResponse newData = new OrderResponse();
        BeanUtils.copyProperties(data, newData);
        if(data.getUser() != null)  {
            newData.setUser(new UserRelationResponse(data.getUser().getId(),
                    data.getUser().getName(), data.getUser().getEmail(),
                    data.getUser().getGender(),
                    data.getUser().getAvatar()));
        }
        return newData;
    }

    public CommentResponse getCommentResponse(Comment data) {
        if(data == null) {
            return null;
        }
        CommentResponse newData = new CommentResponse();
        BeanUtils.copyProperties(data, newData);
        if(data.getUser() != null)  {
            newData.setUser(new UserRelationResponse(data.getUser().getId(),
                    data.getUser().getName(), data.getUser().getEmail(),
                    data.getUser().getGender(),
                    data.getUser().getAvatar()));
        }
        if(data.getRestaurant() != null)  {
            newData.setRestaurant(getRestaurantInfo(data.getRestaurant()));
        }
        return newData;
    }

}
