package doan._java_food.serviceApi.User;

import doan._java_food.requestDto.UserRequestDto;
import doan._java_food.response.LoginResponse;
import doan._java_food.response.User.UserResponse;

import java.util.List;

public interface UserApiService {
    UserResponse findById(Long id);

    UserResponse findByAccessToken(String access_token);
    LoginResponse login(UserRequestDto data);
//    Page<User> findAll(int page, int page_size);

    UserRequestDto save(UserRequestDto user);
    UserRequestDto update(int id, UserRequestDto user);
    UserRequestDto changePassword(int id, UserRequestDto user);
    void delete(Long user_id);

    List<UserResponse> findAndCountApi(
            String page, String page_size,
            String status, String name,
            String email,
            String user_type,
            String username
    );

    Integer countTotalConditionApi(String status, String name,
                                String email,
                                String user_type,
                                   String username);
}
