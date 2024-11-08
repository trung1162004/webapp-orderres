package doan._java_food.service.Order;

import doan._java_food.models.Category;
import doan._java_food.models.Order;
import doan._java_food.models.Restaurant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OrderService {

    List<Order> getAllByCondition(int page, int page_size, String status,
                                       String name, String email, String restaurantName,
                                       String user_id);
    Integer countAllByCondition(String status,
                                String name, String email, String restaurantName,
                                String user_id);

    Boolean create(Order data);
    Order findById(Long id);

    Boolean update(Order data);

    Boolean delete(Long id);

}
