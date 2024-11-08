package doan._java_food.serviceApi.Order;

import doan._java_food.requestDto.CooperationRequestDto;
import doan._java_food.requestDto.OrderRequestDto;
import doan._java_food.response.Cooperation.CooperationResponse;
import doan._java_food.response.Cooperation.ListCooperationResponse;
import doan._java_food.response.Order.ListOrderResponse;
import doan._java_food.response.Order.OrderResponse;

public interface OrderService {

    OrderResponse findById(Long id);
    ListOrderResponse findAndCount(int page, int page_size, String status,
                                   String restaurant_name,
                                   String user_id);

    OrderRequestDto save(OrderRequestDto data) ;
    OrderRequestDto update(Long id, OrderRequestDto data);
    void delete(Long data_id);

}
