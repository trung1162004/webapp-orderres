package doan._java_food.serviceApi.Cooperation;

import doan._java_food.requestDto.CooperationRequestDto;
import doan._java_food.requestDto.RestaurantRequestDto;
import doan._java_food.requestDto.UserRestaurantRequestDto;
import doan._java_food.response.Cooperation.CooperationResponse;
import doan._java_food.response.Cooperation.ListCooperationResponse;
import doan._java_food.response.Restaurant.ListRestaurantResponse;
import doan._java_food.response.Restaurant.RestaurantResponse;

public interface CooperationService {

    CooperationResponse findById(Long id);
    ListCooperationResponse findAndCount(int page, int page_size, String status,
                                         String serviceType,
                                         String user_id);

    CooperationRequestDto save(CooperationRequestDto data) ;
    CooperationRequestDto update(Long id, CooperationRequestDto data);
    void delete(Long data_id);

}
