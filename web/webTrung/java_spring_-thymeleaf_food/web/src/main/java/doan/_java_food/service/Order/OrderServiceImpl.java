package doan._java_food.service.Order;

import doan._java_food.models.Category;
import doan._java_food.models.Order;
import doan._java_food.repository.CategoryRepository;
import doan._java_food.repository.OrderRepository;
import doan._java_food.repository.RestaurantCategoryRepository;
import doan._java_food.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;


    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    private String  makeCode(int length) {
        String result = "";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int charactersLength = characters.length();
        for (int i = 0; i < length; i++) {
            result += characters.charAt((int) (Math.random() * charactersLength));
        }
        return result;
    }



    @Override
    public List<Order> getAllByCondition(int page, int page_size,
                                         String status, String name,
                                         String email, String restaurantName, String user_id) {
        return this.repository.findAndCountBy(page * page_size,
                page_size, status, name, email, restaurantName, user_id);
    }

    @Override
    public Integer countAllByCondition(String status, String name, String email, String restaurantName, String user_id) {
        return this.repository.countByCondition(status, name, email, restaurantName, user_id);
    }

    @Override
    public Boolean create(Order data) {
        try{
            data.setCode(makeCode(10));
            this.repository.save(data);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Boolean update(Order data) {
        try{
            this.repository.save(data);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order findById(Long id) {
        return this.repository.findById(id).get();
    }



    @Override
    public Boolean delete(Long id) {
        try{
            this.repository.delete(findById(id));
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
}
