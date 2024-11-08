package doan._java_food.serviceApi.Restaurant;

import doan._java_food.models.*;
import doan._java_food.repository.*;
import doan._java_food.requestDto.RestaurantRequestDto;
import doan._java_food.requestDto.UserRestaurantRequestDto;
import doan._java_food.response.MappingResponseDto;
import doan._java_food.response.Restaurant.ListRestaurantResponse;
import doan._java_food.response.Restaurant.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class RestaurantApiServiceImpl implements RestaurantApiService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    @Autowired
    private UserRestaurantRepository userRestaurantRepository;

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));



    @Autowired
    private MappingResponseDto responseDto;

    public Restaurant createOrUpdateData(RestaurantRequestDto dataRequest, Restaurant oldData) {
        Restaurant newData = oldData;
        if(oldData == null) {
            newData = new Restaurant();
            newData.setCreatedAt(new Date());
        }
        if(dataRequest.getName() != null) {
            newData.setName(dataRequest.getName());
        }
        if(dataRequest.getHot() != null) {
            newData.setHot(dataRequest.getHot());
        }
        if(dataRequest.getHot_discount() != null) {
            newData.setHot_discount(dataRequest.getHot_discount());
        }
        if(dataRequest.getStatus() != null) {
            newData.setStatus(dataRequest.getStatus());
        }
        User user = newData.getUser();
        if(dataRequest.getUser_id() != null) {
            user = userRepository.getById(Long.parseLong(dataRequest.getUser_id() + ""));
            newData.setUser(user);
        }
        if(dataRequest.getLogo() != null) {
            newData.setLogo(dataRequest.getLogo());
        }
        if(dataRequest.getContent() != null) {
            newData.setContent(dataRequest.getContent());
        }
        if(dataRequest.getAddress() != null) {
            newData.setAddress(dataRequest.getAddress());
        }
        if(dataRequest.getDescription() != null) {
            newData.setDescription(dataRequest.getDescription());
        }
        if(dataRequest.getPhone() != null) {
            newData.setPhone(dataRequest.getPhone());
        }
        if(dataRequest.getEmail() != null) {
            newData.setEmail(dataRequest.getEmail());
        }
        newData.setType("RESTAURANT");
        if(dataRequest.getPrice() != null) {
            newData.setPrice(dataRequest.getPrice());
        }
        if(dataRequest.getQuantity() != null) {
            newData.setQuantity(dataRequest.getQuantity());
        }
        if(dataRequest.getWorkTimeOpen() != null) {
            newData.setWorkTimeOpen(dataRequest.getWorkTimeOpen());
        }
        if(dataRequest.getWorkTimeClose() != null) {
        	newData.setWorkTimeClose(dataRequest.getWorkTimeClose());
        }
        if(dataRequest.getFit() != null) {
        	newData.setFit(dataRequest.getFit());
        }
        if(dataRequest.getSpecialDish() != null) {
        	newData.setSpecialDish(dataRequest.getSpecialDish());
        }
        if(dataRequest.getSpace() != null) {
        	newData.setSpace(dataRequest.getSpace());
        }
        if(dataRequest.getParking() != null) {
        	newData.setParking(dataRequest.getParking());
        }
        if(dataRequest.getFeature() != null) {
        	newData.setFeature(dataRequest.getFeature());
        }
        if(dataRequest.getUtilities() != null) {
        	newData.setUtilities(dataRequest.getUtilities());
        }
        if(dataRequest.getDepositRegulation() != null) {
        	newData.setDepositRegulation(dataRequest.getDepositRegulation());
        }
        if(dataRequest.getDiscountRegulation() != null) {
        	newData.setDiscountRegulation(dataRequest.getDiscountRegulation());
        }
        if(dataRequest.getPasgoTimeRegulation() != null) {
        	newData.setPasgoTimeRegulation(dataRequest.getPasgoTimeRegulation());
        }
        if(dataRequest.getReservationTimeRegulation() != null) {
        	newData.setReservationTimeRegulation(dataRequest.getReservationTimeRegulation());
        }
        if(dataRequest.getHoldTimeRegulation() != null) {
        	newData.setHoldTimeRegulation(dataRequest.getHoldTimeRegulation());
        }
        if(dataRequest.getMinimumGuestsRegulation() != null) {
        	newData.setMinimumGuestsRegulation(dataRequest.getMinimumGuestsRegulation());
        }
        if(dataRequest.getInvoiceRegulation() != null) {
        	newData.setInvoiceRegulation(dataRequest.getInvoiceRegulation());
        }
        if(dataRequest.getServiceFeeRegulation() != null) {
        	newData.setServiceFeeRegulation(dataRequest.getServiceFeeRegulation());
        }
        if(dataRequest.getBringInFeeRegulation() != null) {
        	newData.setBringInFeeRegulation(dataRequest.getBringInFeeRegulation());
        }
        
       if(!dataRequest.getCategory_ids().isEmpty()) {
           for (Integer i: dataRequest.getCategory_ids()) {
               Optional<Category> ca = this.categoryRepository.findById(Long.valueOf(i));
               if(ca.isEmpty()) {
                    throw new RuntimeException("category không tồn tại");
               }
           }
       }
        newData.setUpdatedAt(new Date());



        return newData;
    }
    @Override
    public RestaurantResponse findById(Long id) {
        return responseDto.getRestaurantInfo(repository.getById(id));

    }

    @Override
    public List<Location> getAllLocation() {
        return this.locationRepository.findAll();
    }

    @Override
    public ListRestaurantResponse findAndCount(int page, int page_size, String status,
                                               String name, String email, String type,
                                               String user_id, String location_id, String discount,
                                               String price_from, String price_to) {
        List<Restaurant> results = this.repository.findAndCountBy(page * page_size, page_size,
                status,
                name, email, type, user_id, location_id, discount, price_from, price_to);

        Long total = this.repository.countByCondition(status, name, email, type, user_id,
                location_id, discount, price_from, price_to);
        if(total == null) {
            total = Long.valueOf(0);
        }

        ListRestaurantResponse dataListResponse = new ListRestaurantResponse();
        dataListResponse.setTotal(total);
        if(results.isEmpty()) {
            dataListResponse.setData(new ArrayList<>());
            return dataListResponse;
        }
        List<RestaurantResponse> data = new ArrayList<>();
        for (Restaurant item: results) {
            data.add(responseDto.getRestaurantInfo(item));
        }
        dataListResponse.setData(data);
        return dataListResponse;
    }

    @Override
    public ListRestaurantResponse findAndCountLike(int page, int page_size, Integer user_id) {
       List<UserRestaurant> results = this.userRestaurantRepository.getByUser(page * page_size,
               page_size, user_id);
       Long total = this.userRestaurantRepository.countByUserId(user_id);
        if(total == null) {
            total = Long.valueOf(0);
        }
        ListRestaurantResponse dataListResponse = new ListRestaurantResponse();
        dataListResponse.setTotal(total);
        if(results.isEmpty()) {
            dataListResponse.setData(new ArrayList<>());
            return dataListResponse;
        }
        List<RestaurantResponse> data = new ArrayList<>();
        for (UserRestaurant item: results) {
            data.add(responseDto.getRestaurantInfo(item.getRestaurant()));
        }
        dataListResponse.setData(data);
        return dataListResponse;
    }

    @Override
    public RestaurantRequestDto save(RestaurantRequestDto data)  {
        Restaurant o = this.createOrUpdateData(data, null);
        Restaurant newRank = repository.save(o);
        if(!data.getCategory_ids().isEmpty()) {
            for (Integer i: data.getCategory_ids()) {
                System.out.println("category id---------> " + i);
                Category ca = this.categoryRepository.getById(i.longValue());
                if(ca != null) {
                    System.out.println(ca.getId() + "");
                    RestaurantCategory rc = new RestaurantCategory();
                    rc.setRestaurant(newRank);
                    rc.setCategory(ca);
                    this.restaurantCategoryRepository.save(rc);
                }
            }
        }
        return data;
    }

    @Override
    public UserRestaurantRequestDto LikeOrDisLike(UserRestaurantRequestDto data) {
        if(data.getRestaurant_id() != null) {
            UserRestaurant dataLike = this.userRestaurantRepository.findByUserRestaurant(Long.valueOf(data.getRestaurant_id()), data.getUser_id());
            if(dataLike != null) {
                this.userRestaurantRepository.delete(dataLike);
            } else {
                UserRestaurant d = new UserRestaurant();
                d.setRestaurant(this.repository.getById(Long.valueOf(data.getRestaurant_id())));
                d.setUser(this.userRepository.getById(Long.valueOf(data.getUser_id())));
                this.userRestaurantRepository.save(d);
            }
            return data;
        }
        throw new RuntimeException("Nhãn hàng không được để trống");
    }

    @Override
    public RestaurantRequestDto update(Long id, RestaurantRequestDto data)  {
        var c = repository.getById(id);
        if(c != null) {
            Restaurant o = this.createOrUpdateData(data, c);
            Restaurant newRank = repository.save(o);
            if(!data.getCategory_ids().isEmpty()) {
                this.restaurantCategoryRepository.deleteByRestaurantId(o.getId());
                for (Integer i: data.getCategory_ids()) {
                    Category ca = this.categoryRepository.getById(i.longValue());
                    if(ca != null) {
                        RestaurantCategory rc = new RestaurantCategory();
                        rc.setRestaurant(o);
                        rc.setCategory(ca);

                        this.restaurantCategoryRepository.save(rc);
                    }
                }
            }
            return data;
        }
        return null;
    }

    @Override
    public void delete(Long data_id) {
        repository.deleteById(data_id);
    }
}
