package doan._java_food.service.User;

import doan._java_food.models.User;
import doan._java_food.requestDto.UserRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    User findByUserEmail(String email);
    User findUserByEmail(String email);
    User findByResetToken(String token);
    boolean sendResetPasswordEmail(String email);
    User findById(Long id);
    List<User> getAll();
    List<User> findUserByType(String type);

    List<User> getAllUserByTypeAndStatus(String type, Integer status);
    Boolean create(User user);
    Boolean createData(User user);
    
    

    Boolean update(User user);
    Boolean updateInfo(User user, MultipartFile fileAvatar);
    Boolean delete(Long id);

    Integer countTotalUser(String type) ;


    void updatePassword(User user, String newPassword);
}
