package doan._java_food;

import doan._java_food.service.CustomUserDetailService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import java.util.Arrays;
import java.util.List;
@Configuration
@EnableSpringDataWebSupport
public class ConfigSecurity {
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration corsConfig = new CorsConfiguration();
                corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3013")); // Cập nhật domain của frontend
                corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                corsConfig.setAllowedHeaders(Arrays.asList("Content-Type"));
                corsConfig.setAllowCredentials(true);
                return corsConfig;
            }))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/*").permitAll()
                .requestMatchers("/api/payment/**").permitAll()
                .requestMatchers("/admin/**").permitAll()
                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated())
            .formLogin(login -> login
                .loginPage("/login.html")
                .loginProcessingUrl("/login.html")
                .usernameParameter("username").passwordParameter("password")
                .failureUrl("/login.html?error=true")
                .defaultSuccessUrl("/", true))
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll());
        return httpSecurity.build();
    }


    @Bean
    WebSecurityCustomizer webSecurityCustomizer(){
        return (web)->web.ignoring().requestMatchers("/static/**",
                "/assets/**","/uploads/**",
                "/fe/**","/css/**",
                "/blog-detail/**",
                "/images/**",
                "/web/images/**",
                "/category/**",
                "/blog/**",
                "/discount/**",
                "/active/**");
    }
}
