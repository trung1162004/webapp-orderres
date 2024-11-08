package doan._java_food.service;

import doan._java_food.models.CustomUserDetails;
import doan._java_food.models.User;
import doan._java_food.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("account----------> " + username);
        User user = this.userService.findByUsername(username);

        if (user == null) {
            System.out.println("Không thấy tài khoản");
            throw new UsernameNotFoundException("Không tìm thấy tài khoản");
        }
        if(!user.getStatus().equals("ACTIVE")) {
            throw new UsernameNotFoundException("Tài khoản chưa được active");
        }

        Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        return new CustomUserDetails(user, grantedAuthoritySet);
    }
}
