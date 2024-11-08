package doan._java_food.controller.api;

import doan._java_food.common.BaseResponse;
import doan._java_food.common.BusinessErrorCode;
import doan._java_food.common.BusinessException;
import doan._java_food.requestDto.CooperationRequestDto;
import doan._java_food.requestDto.OrderRequestDto;
import doan._java_food.serviceApi.Cooperation.CooperationService;
import doan._java_food.serviceApi.Order.OrderService;
import doan._java_food.serviceApi.User.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private UserApiService userApiService;

    @GetMapping("show/{id}")
    public BaseResponse<?> findOne(@PathVariable Long id) {
        try {
            return BaseResponse.ofSucceeded(service.findById(id));
        } catch (Exception e) {
            log.debug("[SlideService CONTROLLER]------>error findOne", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[SlideService CONTROLLER]------>findOne", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @GetMapping("list")
    public BaseResponse<?> findAll(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String author,
            @RequestParam(name = "page", required = false, defaultValue = "1") String page,
            @RequestParam(name = "restaurant_name", required = false, defaultValue = "") String restaurant_name,
            @RequestParam(name = "user_id", required = false, defaultValue = "") String user_id,
            @RequestParam(name = "status", required = false, defaultValue = "") String status,
            @RequestParam(name = "page_size", required = false, defaultValue = "20") String page_size
    ) {
        try {
            int number_page = 0;
            if (Integer.parseInt(page) > 1) {
                number_page = Integer.parseInt(page) - 1;
            }
            String access_token = author.replace("Bearer ", "");
            var user = userApiService.findByAccessToken(access_token);
            if(user == null) {
                var error = new BusinessException(new BusinessErrorCode(401, "Không tìm thấy user", "Unauthorized", 401));
                return BaseResponse.ofFailed(error);
            }
            if(user_id.isEmpty()) {
                if(!user.getType().equals("ADMIN")) {
                    user_id = user.getId() + "";
                }
            }
            var response = service.findAndCount(number_page, Integer.parseInt(page_size), status, restaurant_name, user_id);
            BaseResponse.Metadata paging = new BaseResponse.Metadata("", number_page, Integer.parseInt(page_size),
                    response.getTotal(), "", null);

            return BaseResponse.ofSucceeded().setMeta(paging).setData(response.getData());
        } catch (Exception e) {
            log.debug("[SlideService CONTROLLER]------>error list", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[SlideService CONTROLLER]------>list", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @PostMapping("store")
    public BaseResponse<?> save( @RequestHeader(HttpHeaders.AUTHORIZATION) String author,@RequestBody OrderRequestDto data) {
        try {
            String access_token = author.replace("Bearer ", "");
            var user = userApiService.findByAccessToken(access_token);
            if(user == null) {
                var error = new BusinessException(new BusinessErrorCode(401, "Không tìm thấy user", "Unauthorized", 401));
                return BaseResponse.ofFailed(error);
            }
            if(data.getUserId() == null) {
                data.setUserId(user.getId());
            }
            if(data.getStatus() == null) {
                data.setStatus("PENDING");
            }
            var newData = service.save(data);
            return BaseResponse.ofSucceeded(newData);
        } catch (Exception e) {
            log.debug("[SlideService CONTROLLER]------>error create", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, e.getMessage(), 400));
            log.error("[SlideService CONTROLLER]------>create", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @PutMapping("update/{id}")
    public BaseResponse<?> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String author, @PathVariable Long id, @RequestBody OrderRequestDto data) {
        try {
            String access_token = author.replace("Bearer ", "");
            var user = userApiService.findByAccessToken(access_token);
            if(user == null) {
                var error = new BusinessException(new BusinessErrorCode(401, "Không tìm thấy user", "Unauthorized", 401));
                return BaseResponse.ofFailed(error);
            }
            return BaseResponse.ofSucceeded(service.update(id, data));
        } catch (Exception e) {
            log.debug("[SlideService CONTROLLER]------>error update", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, e.getMessage(), 400));
            log.error("[SlideService CONTROLLER]------>update", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @DeleteMapping("delete/{id}")
    public BaseResponse delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return BaseResponse.ofSucceeded();
        } catch (Exception e) {
            log.debug("[SlideService CONTROLLER]------>error update", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, e.getMessage(), 400));
            log.error("[SlideService CONTROLLER]------>update", error);
            return BaseResponse.ofFailed(error);
        }
    }
}
