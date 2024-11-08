package doan._java_food.serviceApi.User;


import doan._java_food.models.User;
import doan._java_food.repository.UserRepository;
import doan._java_food.requestDto.UserRequestDto;
import doan._java_food.response.LoginResponse;
import doan._java_food.response.MappingResponseDto;
import doan._java_food.response.User.UserResponse;
import doan._java_food.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
@Slf4j
public class UserApiServiceImpl implements UserApiService {

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;
//
    @Autowired
    private MappingResponseDto responseDto;
//
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;



    private User setData(UserRequestDto requestDto, User data) {
        if(data == null) {
            data = new User();
            data.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        }
        data.setGender(requestDto.getGender());
        data.setEmail(requestDto.getEmail());
        data.setAvatar(requestDto.getAvatar());
        data.setName(requestDto.getName());
        data.setType(requestDto.getType());
        data.setUsername(requestDto.getUsername());
        data.setStatus(requestDto.getStatus());
        return data;
    }
    @Override
    public UserResponse findById(Long id) {
        User u = userRepository.getById(id);
        return responseDto.getUserInfo(u);
    }

    @Override
    public UserResponse findByAccessToken(String access_token) {
        User u = userRepository.findUserByAccessToken(access_token);
        if(u == null) {
            return null;
        }
        return responseDto.getUserInfo(u);
    }

    @Override
    public LoginResponse login(UserRequestDto data) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        data.getEmail(),
                        data.getPassword()
                )
        );

        System.out.println("11111111111");
        var user = userRepository.findByEmail(data.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getStatus().equals("ACTIVE")) {
            throw new RuntimeException("USER not active");
        }
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        user.setAccessToken(jwtToken);
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
        log.debug("token=========> " + jwtToken);
        return new LoginResponse(jwtToken, refreshToken, responseDto.getUserInfo(user));


    }

    @Override
    public UserRequestDto save(UserRequestDto user) {
        User u = setData(user, null);
        User newData = userRepository.save(u);
        return user;
    }

    @Override
    public UserRequestDto update(int id, UserRequestDto user) {
        User u = userRepository.getById(Long.parseLong(id + ""));
        u = setData(user, u);
        userRepository.save(u);
        return user;
    }

    @Override
    public UserRequestDto changePassword(int id, UserRequestDto user) {
        User u = userRepository.getById(Long.parseLong(id + ""));
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(u);
        return user;
    }

    @Override
    public void delete(Long user_id) {
        userRepository.deleteById(user_id);;
    }

    @Override
    public List<UserResponse> findAndCountApi(String page, String page_size,
                                           String status, String name,
                                           String email,
                                           String user_type,
                                              String username) {
        List<User> data = this.userRepository.findAndCountApi((parseInt(page) - 1) * parseInt(page_size),
                parseInt(page_size), status, name, email, user_type, username
        );
        List<UserResponse> users = new ArrayList<>();
        if(!data.isEmpty()) {
            for (User item: data) {
                users.add(responseDto.getUserInfo(item));
            }
        }


        return users;
    }
    @Override
    public Integer countTotalConditionApi( String status, String name,
                                        String email,
                                        String user_type,
                                           String username) {
        return this.userRepository.countByConditionsApi(status, name, email,
                user_type, username);
    }
}
