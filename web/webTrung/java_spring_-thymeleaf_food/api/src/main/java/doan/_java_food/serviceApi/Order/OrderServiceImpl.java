package doan._java_food.serviceApi.Order;

import doan._java_food.models.Order;
import doan._java_food.models.Restaurant;
import doan._java_food.models.User;
import doan._java_food.repository.*;
import doan._java_food.requestDto.OrderRequestDto;
import doan._java_food.response.MappingResponseDto;
import doan._java_food.response.Order.ListOrderResponse;
import doan._java_food.response.Order.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private MappingResponseDto responseDto;

    private String  makeCode(int length) {
        String result = "";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int charactersLength = characters.length();
        for (int i = 0; i < length; i++) {
            result += characters.charAt((int) (Math.random() * charactersLength));
        }
        return result;
    }

    public Order createOrUpdateData(OrderRequestDto dataRequest, Order oldData) {
        Order newData = oldData;
        if(oldData == null) {
            newData = new Order();
            newData.setCode(makeCode(10));
            newData.setCreatedAt(new Date());
            newData.setType("BOOKING");
            newData.setStatus("PENDING");
            newData.setPaymentStatus("UNPAID");

        }
        if(dataRequest.getCode() != null) {
            newData.setCode(dataRequest.getCode());
        }
//        if(dataRequest.get() != null) {
//            newData.setCode(dataRequest.getCode());
//        }
        if(dataRequest.getStatus() != null) {
            newData.setStatus(dataRequest.getStatus());
        }
        if(dataRequest.getReceiverEmail() != null) {
            newData.setReceiverEmail(dataRequest.getReceiverEmail());
        }

        if(dataRequest.getUserId() != null) {
            User user = userRepository.getById(Long.parseLong(dataRequest.getUserId() + ""));
            newData.setUser(user);
        }
        if(dataRequest.getReceiverName() != null) {
            newData.setReceiverName(dataRequest.getReceiverName());
        }

        if(dataRequest.getReceiverPhone() != null) {
            newData.setReceiverPhone(dataRequest.getReceiverPhone());
        }
        if(dataRequest.getReceiverAddress() != null) {
            newData.setReceiverAddress(dataRequest.getReceiverAddress());
        }
        if(dataRequest.getPrice() != null) {
            newData.setPrice(dataRequest.getPrice());
        }
        if(dataRequest.getQuantity() != null) {
            newData.setQuantity(dataRequest.getQuantity());
        }
        if(dataRequest.getTotalPrice() != null) {
            newData.setTotalPrice(dataRequest.getTotalPrice());
        }
        if(dataRequest.getDiscount() != null) {
            newData.setDiscount(dataRequest.getDiscount());
        }
        if(dataRequest.getTimeBooking() != null) {
            newData.setTimeBooking(LocalDateTime.parse(dataRequest.getTimeBooking()));
        }
        if(dataRequest.getRestaurantName() != null) {
            newData.setRestaurantName(dataRequest.getRestaurantName());
        }
        if(dataRequest.getRestaurantId() != null) {

            newData.setRestaurantId(dataRequest.getRestaurantId());
            Restaurant restaurant = this.restaurantRepository.getById(Long.valueOf(dataRequest.getRestaurantId()));
            if(restaurant == null) {
                throw new RuntimeException("Not found restaurant by id");
            }
            newData.setRestaurantName(restaurant.getName());
            newData.setPrice(restaurant.getPrice());
            newData.setTotalPrice(restaurant.getPrice());
            newData.setLogo(restaurant.getLogo());
            newData.setDiscount(restaurant.getDiscount());
        }
        if(dataRequest.getLogo() != null) {
            newData.setLogo(dataRequest.getLogo());
        }
        if(dataRequest.getPrice() != null) {
            newData.setPrice(dataRequest.getPrice());
        }
        if(dataRequest.getQuantity() != null) {
            newData.setQuantity(dataRequest.getQuantity());
        }
        if(dataRequest.getTotalPrice() != null) {
            newData.setTotalPrice(dataRequest.getTotalPrice());
        }

        if(dataRequest.getNote() != null) {
            newData.setNote(dataRequest.getNote());
        }

        if(dataRequest.getLogo() != null) {
            newData.setLogo(dataRequest.getLogo());
        }
//        if(dataRequest.getTimeBooking() != null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//
//            LocalDateTime dateTime = LocalDateTime.parse(dataRequest.getTimeBooking(), formatter);
//            System.out.println("Parsed LocalDateTime: " + dateTime);
//
//            newData.setTimeBooking(dateTime);
//        }
        newData.setUpdatedAt(new Date());

        return newData;
    }
    @Override
    public OrderResponse findById(Long id) {
        return responseDto.getOrderResponse(repository.getById(id));

    }

    @Override
    public ListOrderResponse findAndCount(int page, int page_size,
                                          String status,
                                          String restaurant_name,
                                          String user_id) {

        List<Order> results = this.repository.findAndCountBy(page * page_size, page_size,
                status, "", "",restaurant_name, user_id);

        Integer total = this.repository.countByCondition(status, "", "",restaurant_name, user_id);
        if(total == null) {
            total = 0;
        }

        ListOrderResponse dataListResponse = new ListOrderResponse();

        dataListResponse.setTotal(Long.valueOf(total));

        if(results.isEmpty()) {
            dataListResponse.setData(new ArrayList<>());
            return dataListResponse;
        }
        List<OrderResponse> data = new ArrayList<>();
        for (Order item: results) {
            data.add(responseDto.getOrderResponse(item));
        }
        dataListResponse.setData(data);
        return dataListResponse;
    }


    @Override
    public OrderRequestDto save(OrderRequestDto data)  {
        data.setStatus("PENDING");
        Order o = this.createOrUpdateData(data, null);
        Order newRank = repository.save(o);
        return data;
    }



    @Override
    public OrderRequestDto update(Long id, OrderRequestDto data)  {
        var c = repository.getById(id);
        if(c != null) {
            Order o = this.createOrUpdateData(data, c);
            Order newRank = repository.save(o);
            return data;
        }
        return null;
    }

    @Override
    public void delete(Long data_id) {
        repository.deleteById(data_id);
    }
}
