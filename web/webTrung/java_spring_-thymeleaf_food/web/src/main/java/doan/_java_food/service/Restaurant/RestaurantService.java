package doan._java_food.service.Restaurant;

import doan._java_food.models.Location;
import doan._java_food.models.Restaurant;
import doan._java_food.models.Slides;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();
    List<Location> getAllLocation();
    void createOrUpdateLocation(String name);
    List<Restaurant> getAllByCondition(int page, int page_size, String status,
                                       String name, String email, String type,
                                       String user_id, String category_id, String hot, String discount,
                                       String price_from, String price_to
    );
    Integer countAllByCondition(String status, String name,
                                String email, String type,
                                String user_id, String category_id,
                                String hot, String discount,
                                String price_from, String price_to
                                );

    Boolean create(Restaurant data, MultipartFile fileAvatar);
    Restaurant findById(Long id);

    Boolean update(Restaurant data, MultipartFile fileAvatar);
    Boolean updateType(Restaurant data);

    Boolean delete(Long id);

}
