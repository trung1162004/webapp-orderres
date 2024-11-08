package doan._java_food.serviceApi.Cooperation;

import doan._java_food.models.*;
import doan._java_food.repository.*;
import doan._java_food.requestDto.CooperationRequestDto;
import doan._java_food.requestDto.RestaurantRequestDto;
import doan._java_food.requestDto.UserRestaurantRequestDto;
import doan._java_food.response.Cooperation.CooperationResponse;
import doan._java_food.response.Cooperation.ListCooperationResponse;
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
public class CooperationServiceImpl implements CooperationService {

    @Autowired
    private UserServiceRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    @Autowired
    private UserRestaurantRepository userRestaurantRepository;




    @Autowired
    private MappingResponseDto responseDto;

    public UserService createOrUpdateData(CooperationRequestDto dataRequest, UserService oldData) {
        UserService newData = oldData;
        if(oldData == null) {
            newData = new UserService();
            newData.setCreatedAt(new Date());
        }
        if(dataRequest.getName() != null) {
            newData.setName(dataRequest.getName());
        }
        if(dataRequest.getStatus() != null) {
            newData.setStatus(dataRequest.getStatus());
        }
        if(dataRequest.getEmail() != null) {
            newData.setEmail(dataRequest.getEmail());
        }
        if(dataRequest.getUser_id() != null) {
           User user = userRepository.getById(Long.parseLong(dataRequest.getUser_id() + ""));
            newData.setUser(user);
        }
        if(dataRequest.getService_type() != null) {
            newData.setService_type(dataRequest.getService_type());
        }

        if(dataRequest.getDescription() != null) {
            newData.setDescription(dataRequest.getDescription());
        }
        if(dataRequest.getPhone() != null) {
            newData.setPhone(dataRequest.getPhone());
        }
        if(dataRequest.getPrice() != null) {
            newData.setPrice(dataRequest.getPrice());
        }

        newData.setUpdatedAt(new Date());

        return newData;
    }
    @Override
    public CooperationResponse findById(Long id) {
        return responseDto.getCooperation(repository.getById(id));

    }

    @Override
    public ListCooperationResponse findAndCount(int page, int page_size,
                                                String status,
                                                String serviceType,
                                                String user_id) {
        List<UserService> results = this.repository.findAndCountBy(page * page_size, page_size,
                user_id,serviceType, status);

        Integer total = this.repository.countByCondition(user_id, serviceType, status);
        if(total == null) {
            total = 0;
        }

        ListCooperationResponse dataListResponse = new ListCooperationResponse();

        dataListResponse.setTotal(Long.valueOf(total));

        if(results.isEmpty()) {
            dataListResponse.setData(new ArrayList<>());
            return dataListResponse;
        }
        List<CooperationResponse> data = new ArrayList<>();
        for (UserService item: results) {
            data.add(responseDto.getCooperation(item));
        }
        dataListResponse.setData(data);
        return dataListResponse;
    }


    @Override
    public CooperationRequestDto save(CooperationRequestDto data)  {
        UserService o = this.createOrUpdateData(data, null);
        UserService newRank = repository.save(o);
        return data;
    }



    @Override
    public CooperationRequestDto update(Long id, CooperationRequestDto data)  {
        var c = repository.getById(id);
        if(c != null) {
            UserService o = this.createOrUpdateData(data, c);
            UserService newRank = repository.save(o);
            return data;
        }
        return null;
    }

    @Override
    public void delete(Long data_id) {
        repository.deleteById(data_id);
    }
}
