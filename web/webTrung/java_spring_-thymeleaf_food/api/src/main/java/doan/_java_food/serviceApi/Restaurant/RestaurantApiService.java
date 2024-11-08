package doan._java_food.serviceApi.Restaurant;

import doan._java_food.models.Location;
import doan._java_food.requestDto.RestaurantRequestDto;
import doan._java_food.requestDto.UserRestaurantRequestDto;
import doan._java_food.response.Restaurant.ListRestaurantResponse;
import doan._java_food.response.Restaurant.RestaurantResponse;

import java.util.List;

public interface RestaurantApiService {

    RestaurantResponse findById(Long id);
    List<Location> getAllLocation();
    ListRestaurantResponse findAndCount(int page, int page_size, String status,
                                        String name, String email, String type,
                                        String user_id, String location_id, String discount,
                                        String price_from, String price_to);

    ListRestaurantResponse findAndCountLike(int page, int page_size, Integer user_id);
    RestaurantRequestDto save(RestaurantRequestDto data) ;
    UserRestaurantRequestDto LikeOrDisLike(UserRestaurantRequestDto data);
    RestaurantRequestDto update(Long id, RestaurantRequestDto data);
    void delete(Long data_id);

}
