package doan._java_food.controller.api;

import doan._java_food.common.BaseResponse;
import doan._java_food.common.BusinessErrorCode;
import doan._java_food.common.BusinessException;
import doan._java_food.requestDto.UserRequestDto;
import doan._java_food.serviceApi.User.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/upload")
@Slf4j
public class UploadController {
    @Autowired
    private UserApiService service;

    @PostMapping("file")
    public BaseResponse<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            File convFile = new File("uploads/" + file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            return BaseResponse.ofSucceeded().setData(convFile.getName());
        } catch (Exception e) {
            String message = e.getMessage();
            log.error("[OCR CONTROLLER]------>create" + message);
            var error = new BusinessException(new BusinessErrorCode(400, message, message, 400));
            log.error("[OCR CONTROLLER]------>create" + message);
            return BaseResponse.ofFailed(error);
        }
    }

    @GetMapping("/file/{name}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String name) {
        // Load file as Resource
        Path filePath = Paths.get("uploads/").resolve(name).normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                // Determine content type dynamically
                String contentType = "image/jpeg"; // Modify according to your image type
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException ex) {
            return ResponseEntity.notFound().build();
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
