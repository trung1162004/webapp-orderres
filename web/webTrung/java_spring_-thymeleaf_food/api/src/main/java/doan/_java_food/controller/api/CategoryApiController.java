package doan._java_food.controller.api;

import doan._java_food.common.BaseResponse;
import doan._java_food.common.BusinessErrorCode;
import doan._java_food.common.BusinessException;
import doan._java_food.requestDto.CategoryRequestDto;
import doan._java_food.serviceApi.Category.CategoryApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/category")
@Slf4j
public class CategoryApiController {
    @Autowired
    private CategoryApiService service;

    @GetMapping("show/{id}")
    public BaseResponse<?> findOne(@PathVariable Long id) {
        try {
            return BaseResponse.ofSucceeded(service.findById(id));
        } catch (Exception e) {
            log.debug("[CATEGORY CONTROLLER]------>error findOne", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[CATEGORY CONTROLLER]------>findOne", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @GetMapping("list")
    public BaseResponse<?> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") String page,
            @RequestParam(name = "page_size", required = false, defaultValue = "20") String page_size
    ) {
        try {
            int number_page = 0;
            if (Integer.parseInt(page) > 1) {
                number_page = Integer.parseInt(page) - 1;
            }
            var response = service.findAll(number_page, Integer.parseInt(page_size));
            BaseResponse.Metadata paging = new BaseResponse.Metadata("", number_page, Integer.parseInt(page_size), response.getTotal(), "", null);

            return BaseResponse.ofSucceeded().setMeta(paging).setData(response.getData());
        } catch (Exception e) {
            log.debug("[CATEGORY CONTROLLER]------>error list", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[CATEGORY CONTROLLER]------>list", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @PostMapping("store")
    public BaseResponse<?> save(@RequestBody CategoryRequestDto data) {
        try {
            var newData = service.save(data);
            return BaseResponse.ofSucceeded(newData);
        } catch (Exception e) {
            log.debug("[CATEGORY CONTROLLER]------>error create", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, "Tạo mới thất bại", 400));
            log.error("[CATEGORY CONTROLLER]------>create", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @PutMapping("update/{id}")
    public BaseResponse<?> update(@PathVariable Long id, @RequestBody CategoryRequestDto data) {
        try {
            return BaseResponse.ofSucceeded(service.update(id, data));
        } catch (Exception e) {
            log.debug("[CATEGORY CONTROLLER]------>error update", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, "Cập nhật thất bại", 400));
            log.error("[CATEGORY CONTROLLER]------>update", error);
            return BaseResponse.ofFailed(error);
        }
    }

    @DeleteMapping("delete/{id}")
    public BaseResponse delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return BaseResponse.ofSucceeded();
        } catch (Exception e) {
            log.debug("[CATEGORY CONTROLLER]------>error update", e);
            String message = e.getMessage();
            var error = new BusinessException(new BusinessErrorCode(400, message, "Xóa thất bại", 400));
            log.error("[CATEGORY CONTROLLER]------>update", error);
            return BaseResponse.ofFailed(error);
        }
    }
}
