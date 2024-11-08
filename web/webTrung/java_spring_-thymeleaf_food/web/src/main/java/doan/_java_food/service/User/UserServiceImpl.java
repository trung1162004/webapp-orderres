package doan._java_food.service.User;


import doan._java_food.models.User;
import doan._java_food.repository.UserRepository;
import doan._java_food.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    @Override
    public User findByUsername(String username) {
        User u =  this.userRepository.findByUsername(username);
        return u;
    }

    @Override
    public User findByUserEmail(String email) {
        return this.userRepository.findByUserEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email);
    }
    
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
    

    @Override
    public User findByResetToken(String token) {
        return userRepository.findUserByAccessToken(token);
    }

    @Override
    public boolean sendResetPasswordEmail(String email) {
        User user = this.userRepository.findByUserEmail(email);
        if (user == null) {
            return false;
        }

        String token = UUID.randomUUID().toString();
        user.setAccessToken(token);
        this.userRepository.save(user);

        Map<String, Object> model2 = new HashMap<>();
        model2.put("name", user.getName());
        model2.put("link", "http://localhost:3013/reset-password?token=" + token);
        this.emailService.sendEmail(user.getEmail(),"Reset Password",model2,"pages/mail/forgot-password");
        return true;
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> findUserByType(String type) {
        return this.userRepository.findUserByType(type);
    }

    @Override
    public List<User> getAllUserByTypeAndStatus(String type, Integer status) {
//        return this.userRepository.findAllByTypeAndStatus(type, status);
        return null;
    }

    @Override
    public Boolean create(User user) {
        try {
            System.out.println("=================== USER REGISTER: " + user);
            this.userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean createData(User user) {
        try {
            System.out.println("=================== USER REGISTER data: " + user);
            User newData = setData(user, null);
            this.userRepository.save(newData);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(User rq) {
        try {
            System.out.println("id user-----------------------------> " + rq.getId());
            User u = this.userRepository.findById(Long.parseLong(rq.getId() + "") ).get();
            if(u == null) {
                return false;
            }
            u = setData(rq, u);

            this.userRepository.save(u);
            return true;
        }catch (Exception e) {
            System.out.println("============ERRORS update user: " + e);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateInfo(User user, MultipartFile file) {
        try{
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("images");
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            }
            Path image = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(Objects.requireNonNull(file.getOriginalFilename()));

            if (!file.isEmpty()) {
                try (OutputStream os = Files.newOutputStream(image)) {
                    os.write(file.getBytes());
                    user.setAvatar(file.getOriginalFilename());
                } catch (Exception e) {
                    System.out.println("error create file-----------> "+e.getMessage());
                }
            }

            this.userRepository.save(user);
            return true;
        }catch (Exception e) {
            System.out.println("error create company-----------> "+e.getMessage());
            return false;
        }
    }

    private User setData(User rq, User u) {
        if(u == null) {
            u = new User();
            u.setPassword(passwordEncoder.encode(rq.getPassword()));
            u.setUsername(rq.getUsername());
        }
        if(rq.getEmail() != "" && rq.getEmail() != null) {
            u.setEmail(rq.getEmail());
        }
        if(rq.getPhone() != "" && rq.getPhone() != null) {
            u.setPhone(rq.getPhone());
        }
        if(rq.getStatus() != null && rq.getStatus() != "") {
            u.setStatus(rq.getStatus());
        }
        if(rq.getType() != "" && rq.getType() != null) {
            u.setType(rq.getType());
        }
        if(rq.getGender() != "" && rq.getGender() != null) {
            u.setGender(rq.getGender());
        }
        if(rq.getAddress() != "" && rq.getAddress() != null) {
            u.setAddress(rq.getAddress());
        }
        if(rq.getName() != "" && rq.getName() != null) {
            u.setName(rq.getName());
        }
        return u;
    }

    @Override
    public Boolean delete(Long id) {
        try{
            this.userRepository.delete(findById(id));
            return true;
        }catch (Exception e) {
            System.out.println("============ERRORS user JOB: " + e);
            e.printStackTrace();
        }
        return  false;
    }

    @Override
    public Integer countTotalUser(String type) {
        return this.userRepository.countUserByType(type);
    }

    public void updatePassword(User user, String newPassword) {
        System.out.println("========= UPDATE PASSSS : " + newPassword);
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setAccessToken(null);
        userRepository.save(user);
    }
}
