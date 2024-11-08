package doan._java_food.controller.api;

import doan._java_food.common.BaseResponse;
import doan._java_food.common.BusinessErrorCode;
import doan._java_food.common.BusinessException;
import doan._java_food.requestDto.RestaurantRequestDto;
import doan._java_food.requestDto.UserRestaurantRequestDto;
import doan._java_food.serviceApi.Restaurant.RestaurantApiService;
import doan._java_food.serviceApi.User.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/restaurant")
@Slf4j
public class RestaurantAPIController {
    @Autowired
    private RestaurantApiService service;

    @Autowired
    private UserApiService userApiService;

    @GetMapping("location")
    public BaseResponse<?> findLocation() {
        try {
            return BaseResponse.ofSucceeded(service.getAllLocation());
        } catch (RuntimeException e) {
            log.debug("[RESTAURANT CONTROLLER]------>error findOne", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[RESTAURANT CONTROLLER]------>findOne", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @GetMapping("show/{id}")
    public BaseResponse<?> findOne(@PathVariable Long id) {
        try {
            return BaseResponse.ofSucceeded(service.findById(id));
        } catch (RuntimeException e) {
            log.debug("[RESTAURANT CONTROLLER]------>error findOne", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[RESTAURANT CONTROLLER]------>findOne", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @GetMapping("list")
    public BaseResponse<?> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") String page,
            @RequestParam(name = "page_size", required = false, defaultValue = "20") String page_size,
            @RequestParam(name = "status", required = false, defaultValue = "") String status,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "email", required = false, defaultValue = "") String email,
            @RequestParam(name = "user_id", required = false, defaultValue = "") String user_id,
            @RequestParam(name = "location_id", required = false, defaultValue = "") String location_id,
            @RequestParam(name = "hot_discount", required = false, defaultValue = "") String hot_discount,
            @RequestParam(name = "price_from", required = false, defaultValue = "") String price_from,
            @RequestParam(name = "price_to", required = false, defaultValue = "") String price_to
    ) {
        try {
            int number_page = 0;
            if (Integer.parseInt(page) > 1) {
                number_page = Integer.parseInt(page) - 1;
            }
            var response = service.findAndCount(number_page, Integer.parseInt(page_size),
                    status, name, email, "RESTAURANT", user_id, location_id,
                    hot_discount, price_from, price_to);
            BaseResponse.Metadata paging = new BaseResponse.Metadata("", number_page,
                    Integer.parseInt(page_size), response.getTotal(), "", null);

            return BaseResponse.ofSucceeded().setMeta(paging).setData(response.getData());
        } catch (RuntimeException e) {
            log.debug("[RESTAURANT CONTROLLER]------>error list", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[RESTAURANT CONTROLLER]------>list", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @PostMapping("store")
    public BaseResponse<?> save(@RequestBody RestaurantRequestDto data) {
        try {
            var newData = service.save(data);
            return BaseResponse.ofSucceeded(newData);
        } catch (RuntimeException e) {
            log.debug("[RESTAURANT CONTROLLER]------>error create" + e.getMessage());
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[RESTAURANT CONTROLLER]------>create", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @PostMapping("like-or-unlike")
    public BaseResponse<?> likeOrUnlike(@RequestBody UserRestaurantRequestDto data, @RequestHeader(HttpHeaders.AUTHORIZATION) String author) {
        try {
            String access_token = author.replace("Bearer ", "");
            var user = userApiService.findByAccessToken(access_token);
            if(user == null) {
                var error = new BusinessException(new BusinessErrorCode(401, "Không tìm thấy user", "Unauthorized", 401));
                return BaseResponse.ofFailed(error);
            }
            if(data.getUser_id() == null) {
                data.setUser_id(user.getId())  ;
            }
            var newData = service.LikeOrDisLike(data);
            return BaseResponse.ofSucceeded(newData);
        } catch (RuntimeException e) {
            log.debug("[RESTAURANT CONTROLLER]------>error create", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[RESTAURANT CONTROLLER]------>create", error);
            return BaseResponse.ofFailed(error);
        }
    }
    @GetMapping("like")
    public BaseResponse<?> likeList(@RequestParam(name = "page", required = false, defaultValue = "1") String page,
                                        @RequestParam(name = "page_size", required = false, defaultValue = "20") String page_size,
                                    @RequestParam(name = "user_id", required = false, defaultValue = "") String user_id,
                                    @RequestHeader(HttpHeaders.AUTHORIZATION) String author
    ) {
        try {
            String access_token = author.replace("Bearer ", "");
            var user = userApiService.findByAccessToken(access_token);
            if(user == null) {
                var error = new BusinessException(new BusinessErrorCode(401, "Không tìm thấy user", "Unauthorized", 401));
                return BaseResponse.ofFailed(error);
            }
            int number_page = 0;
            if (Integer.parseInt(page) > 1) {
                number_page = Integer.parseInt(page) - 1;
            }
            Integer userId = null;
            if(user_id.equals("") ) {
                userId = user.getId();
            }

            var response = service.findAndCountLike(number_page, Integer.parseInt(page_size),  userId);
            BaseResponse.Metadata paging = new BaseResponse.Metadata("", number_page,
                    Integer.parseInt(page_size), response.getTotal(), "", null);

            return BaseResponse.ofSucceeded().setMeta(paging).setData(response.getData());
        } catch (RuntimeException e) {
            log.debug("[RESTAURANT CONTROLLER]------>error create", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[RESTAURANT CONTROLLER]------>create", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @PutMapping("update/{id}")
    public BaseResponse<?> update(@PathVariable Long id, @RequestBody RestaurantRequestDto data) {
        try {
            return BaseResponse.ofSucceeded(service.update(id, data));
        } catch (RuntimeException e) {
            log.debug("[RESTAURANT CONTROLLER]------>error update", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[RESTAURANT CONTROLLER]------>update", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @DeleteMapping("delete/{id}")
    public BaseResponse delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return BaseResponse.ofSucceeded();
        } catch (RuntimeException e) {
            log.debug("[RESTAURANT CONTROLLER]------>error update", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[RESTAURANT CONTROLLER]------>update", error);
            return BaseResponse.ofFailed(error);
        }
    }
}
