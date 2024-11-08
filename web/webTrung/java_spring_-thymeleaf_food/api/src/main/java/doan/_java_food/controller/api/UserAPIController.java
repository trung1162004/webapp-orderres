package doan._java_food.controller.api;

import doan._java_food.common.BaseResponse;
import doan._java_food.common.BusinessErrorCode;
import doan._java_food.common.BusinessException;
import doan._java_food.requestDto.UserRequestDto;
import doan._java_food.serviceApi.User.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class UserAPIController {

    @Autowired
    private UserApiService userApiService;



    @GetMapping("list")
    public BaseResponse<?> getLists(@RequestParam(name = "page", required = false, defaultValue = "1") String page,
                                    @RequestParam(name = "page_size", required = false, defaultValue = "20") String page_size,
                                    @RequestParam(name = "status", required = false, defaultValue = "") String status,
                                    @RequestParam(name = "name", required = false, defaultValue = "") String name,
                                    @RequestParam(name = "email", required = false, defaultValue = "") String email,
                                    @RequestParam(name = "type", required = false, defaultValue = "") String user_type) {
        try {
            int number_page = 0;
            if(Integer.parseInt(page) > 1) {
                number_page = Integer.parseInt(page) - 1;
            }
            var users = userApiService.findAndCountApi(page, page_size,status, name, email, user_type, "");
            Integer total = userApiService.countTotalConditionApi(status, name, email,user_type, "");
            BaseResponse.Metadata paging = new BaseResponse.Metadata("", number_page ,  Integer.parseInt(page_size), Long.parseLong(total + ""), "", null);
            return BaseResponse.ofSucceeded().setData(users).setMeta(paging);
        } catch (Exception e) {
            log.debug("[USER CONTROLLER]------>error list", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[USER CONTROLLER]------>list", error);
            return BaseResponse.ofFailed(error);
        }
    }


    @GetMapping("show/{id}")
    public BaseResponse<?> findOne(@PathVariable Long id) {
        try {
            return BaseResponse.ofSucceeded(userApiService.findById(id));
        } catch (Exception e) {
            log.debug("[USER CONTROLLER]------>error findOne", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[USER CONTROLLER]------>findOne", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @PostMapping("store")
    public BaseResponse<?> save(@RequestBody UserRequestDto data) {
        try {
            return BaseResponse.ofSucceeded(userApiService.save(data));
        } catch (Exception e) {
            log.debug("[USER CONTROLLER]------>error create", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, "Tạo mới thất bại", 400));
            log.error("[USER CONTROLLER]------>create", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @PutMapping("update/{id}")
    public BaseResponse<?> update(@PathVariable Integer id,@RequestBody UserRequestDto data) {
        try {
            return BaseResponse.ofSucceeded(userApiService.update(id, data));
        } catch (Exception e) {
            log.debug("[USER CONTROLLER]------>error update", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, "Cập nhật thất bại", 400));
            log.error("[USER CONTROLLER]------>update", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @DeleteMapping("delete/{id}")
    public BaseResponse delete(@PathVariable Long id) {
        try {
            userApiService.delete(id);
            return BaseResponse.ofSucceeded();
        } catch (Exception e) {
            log.debug("[BonusAndDisciplineRequest CONTROLLER]------>error update", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, "Cập nhật thất bại", 400));
            log.error("[BonusAndDisciplineRequest CONTROLLER]------>update", error);
            return BaseResponse.ofFailed(error);
        }
    }
}
