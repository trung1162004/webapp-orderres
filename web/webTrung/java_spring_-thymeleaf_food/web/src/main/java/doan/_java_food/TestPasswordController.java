package doan._java_food;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPasswordController {
    public static void main(String[] args) {
        System.out.print(new BCryptPasswordEncoder().encode("123456789"));
    }
}
