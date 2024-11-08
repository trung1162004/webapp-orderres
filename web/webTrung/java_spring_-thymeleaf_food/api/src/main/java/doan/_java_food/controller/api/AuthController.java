package doan._java_food.controller.api;

import doan._java_food.common.BaseResponse;
import doan._java_food.common.BusinessErrorCode;
import doan._java_food.common.BusinessException;
import doan._java_food.requestDto.UserRequestDto;
import doan._java_food.serviceApi.User.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthController {
    @Autowired
    private UserApiService service;

    @GetMapping("profile")
    public BaseResponse<?> findOne(@RequestHeader(HttpHeaders.AUTHORIZATION) String author) {
        try {
            String access_token = author.replace("Bearer ", "");
            return BaseResponse.ofSucceeded(service.findByAccessToken(access_token));
        } catch (Exception e) {
            log.debug("[USER CONTROLLER]------>error findOne", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[USER CONTROLLER]------>findOne", error);
            return BaseResponse.ofFailed(error);
        }
    }


    @PostMapping("register")
    public BaseResponse<?> save(@RequestBody UserRequestDto data) {
        try {
            if(data.getStatus() == null) {
                data.setStatus("ACTIVE");
            }
            if(data.getUsername() != null) {
                Integer totalUserName = service.countTotalConditionApi("", "", "", "", data.getUsername().trim());
                if(totalUserName > 0) {
                    var error = new BusinessException(new BusinessErrorCode(400, "Tài khoản đã tồn tại", "Tài khoản đã tồn tại", 400));
                    return BaseResponse.ofFailed(error);

                }
            }
            if(data.getEmail() != null) {
                Integer totalEmail = service.countTotalConditionApi("", "", data.getEmail(), "", "");
                if(totalEmail > 0) {
                    var error = new BusinessException(new BusinessErrorCode(400, "Email đã tồn tại", "Email đã tồn tại", 400));
                    return BaseResponse.ofFailed(error);
                }

            }
            var newData = service.save(data);
            return BaseResponse.ofSucceeded(newData);
        } catch (Exception e) {
            log.debug("[USER CONTROLLER]------>error create", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, "Tạo mới thất bại", 400));
            log.error("[USER CONTROLLER]------>create", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @PostMapping("login")
    public BaseResponse<?> login(@RequestBody UserRequestDto data) {
        try {
            var newData = service.login(data);
            return BaseResponse.ofSucceeded(newData);
        } catch (Exception e) {
            String message = e.getMessage();
            log.error("[USER CONTROLLER]------>error create" + message);

            var error = new BusinessException(new BusinessErrorCode(400, message, e.getMessage(), 400));
            log.error("[USER CONTROLLER]------>create"+ e.toString());
            return BaseResponse.ofFailed(error);
        }
    }

    @PutMapping("profile")
    public BaseResponse<?> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String author, @RequestBody UserRequestDto data) {
        try {
            String access_token = author.replace("Bearer ", "");
            var user = service.findByAccessToken(access_token);
            if(user == null) {
                throw new RuntimeException("Not found user");
            }
            return BaseResponse.ofSucceeded(service.update(user.getId(), data));
        } catch (Exception e) {
            log.debug("[USER CONTROLLER]------>error update", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, "Cập nhật thất bại", 400));
            log.error("[USER CONTROLLER]------>update", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @PutMapping("change-password")
    public BaseResponse<?> updatePassword(@RequestHeader(HttpHeaders.AUTHORIZATION) String author, @RequestBody UserRequestDto data) {
        try {
            String access_token = author.replace("Bearer ", "");
            var user = service.findByAccessToken(access_token);
            if(user == null) {
                throw new RuntimeException("Not found user");
            }
            if(data.getPassword() != null) {
                data.setPassword(data.getPassword().trim());
            }
            if(data.getConfirm_password() != null) {
                data.setConfirm_password(data.getConfirm_password().trim());
            }
            if(data.getPassword() == null || data.getConfirm_password() == null) {
                throw new RuntimeException("Password and Confirm password is required.");
            }
            if(!data.getPassword().equals(data.getConfirm_password())) {
                throw new RuntimeException("Password and Confirm password is not matching.");
            }
            return BaseResponse.ofSucceeded(service.changePassword(user.getId(), data));
        } catch (Exception e) {
            log.debug("[USER CONTROLLER]------>error update", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[USER CONTROLLER]------>update", error);
            return BaseResponse.ofFailed(error);
        }
    }


}
