//package doan._java_food.security;
//
//import doan._java_food.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.data.domain.AuditorAware;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.AuthenticationProvider;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
//
////@Configuration
////@RequiredArgsConstructor
//public class ApplicationConfig {
//
//  @Autowired
//  private UserRepository repository;
//
//  @Bean
//  public UserDetailsService userDetailsService() {
//    return null;
////    return username -> repository.findByEmail(username)
////        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//  }
//
//  @Bean
//  public AuthenticationProvider authenticationProvider() {
//    return null;
////    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
////    authProvider.setUserDetailsService(userDetailsService());
////    authProvider.setPasswordEncoder(passwordEncoder());
////    return authProvider;
//  }
//
//  @Bean
//  public AuditorAware<Integer> auditorAware() {
//    return new ApplicationAuditAware();
//  }
//
//  @Bean
//  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//    return config.getAuthenticationManager();
//  }
////
////  @Bean
////  public PasswordEncoder passwordEncoder() {
////    return new BCryptPasswordEncoder();
////  }
//
//}
