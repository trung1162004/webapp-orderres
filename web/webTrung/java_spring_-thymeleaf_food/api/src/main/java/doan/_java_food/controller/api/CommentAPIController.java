package doan._java_food.controller.api;

import doan._java_food.common.BaseResponse;
import doan._java_food.common.BusinessErrorCode;
import doan._java_food.common.BusinessException;
import doan._java_food.requestDto.CommentRequestDto;
import doan._java_food.requestDto.RestaurantRequestDto;
import doan._java_food.requestDto.UserRestaurantRequestDto;
import doan._java_food.serviceApi.Comment.CommentApiService;
import doan._java_food.serviceApi.Restaurant.RestaurantApiService;
import doan._java_food.serviceApi.User.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/comment")
@Slf4j
public class CommentAPIController {
    @Autowired
    private CommentApiService service;

    @Autowired
    private UserApiService userApiService;

    @GetMapping("show/{id}")
    public BaseResponse<?> findOne(@PathVariable Long id) {
        try {
            return BaseResponse.ofSucceeded(service.findById(id));
        } catch (RuntimeException e) {
            log.debug("[CommentApiService CONTROLLER]------>error findOne", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[CommentApiService CONTROLLER]------>findOne", error);
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
            @RequestParam(name = "restaurant_id", required = false, defaultValue = "") String restaurant_id,
            @RequestParam(name = "user_id", required = false, defaultValue = "") String user_id
    ) {
        try {
            int number_page = 0;
            if (Integer.parseInt(page) > 1) {
                number_page = Integer.parseInt(page) - 1;
            }
            var response = service.findAndCount(number_page, Integer.parseInt(page_size), restaurant_id, user_id, status, name, email);
            BaseResponse.Metadata paging = new BaseResponse.Metadata("", number_page,
                    Integer.parseInt(page_size), response.getTotal(), "", null);

            return BaseResponse.ofSucceeded().setMeta(paging).setData(response.getData());
        } catch (RuntimeException e) {
            log.debug("[CommentApiService CONTROLLER]------>error list", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[CommentApiService CONTROLLER]------>list", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @PostMapping("store")
    public BaseResponse<?> save(@RequestBody CommentRequestDto data, @RequestHeader(HttpHeaders.AUTHORIZATION) String author) {
        try {
            String access_token = author.replace("Bearer ", "");
            var user = userApiService.findByAccessToken(access_token);
            if(user == null) {
                var error = new BusinessException(new BusinessErrorCode(401, "Not found user", "Unauthorized", 401));
                return BaseResponse.ofFailed(error);
            }
            if(data.getUser_id() == null) {
                data.setUser_id(Long.valueOf(user.getId()));
            }
            if(data.getStatus() == null) {
                data.setStatus("APPROVED");
            }
            var newData = service.save(data);
            return BaseResponse.ofSucceeded(newData);
        } catch (RuntimeException e) {
            log.debug("[CommentApiService CONTROLLER]------>error create" + e.getMessage());
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[CommentApiService CONTROLLER]------>create", error);
            return BaseResponse.ofFailed(error);
        }
    }


    @PutMapping("update/{id}")
    public BaseResponse<?> update(@PathVariable Long id, @RequestBody CommentRequestDto data, @RequestHeader(HttpHeaders.AUTHORIZATION) String author) {
        try {
            String access_token = author.replace("Bearer ", "");
            var user = userApiService.findByAccessToken(access_token);
            if(user == null) {
                var error = new BusinessException(new BusinessErrorCode(401, "Not found user", "Unauthorized", 401));
                return BaseResponse.ofFailed(error);
            }
            if(data.getUser_id() == null) {
                data.setUser_id(Long.valueOf(user.getId()));
            }
            return BaseResponse.ofSucceeded(service.update(id, data));
        } catch (RuntimeException e) {
            log.debug("[CommentApiService CONTROLLER]------>error update", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[CommentApiService CONTROLLER]------>update", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @DeleteMapping("delete/{id}")
    public BaseResponse delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return BaseResponse.ofSucceeded();
        } catch (RuntimeException e) {
            log.debug("[CommentApiService CONTROLLER]------>error update", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[CommentApiService CONTROLLER]------>update", error);
            return BaseResponse.ofFailed(error);
        }
    }
}
