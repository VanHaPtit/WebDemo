package com.example.WebDemo;//package com.example.CRUD_SQL_JPA;
//
//import com.example.CRUD_SQL_JPA.Service.CustomUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static com.sun.org.apache.xml.internal.serialize.LineSeparator.Web;
//
//@Configuration
//@EnableWebSecurity
//
//public class Security {
//    @Autowired
//    private CustomUserDetailService customUserDetailService;
//
//    @Bean
//    BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
////    // chatGPT
////@Bean
////SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
////    httpSecurity.csrf(csrf -> csrf.disable())
////            .authorizeHttpRequests(auth -> auth
////                    .requestMatchers("/*").permitAll()
////                    .requestMatchers("/admin/**").hasAuthority("ADMIN")
////                    .anyRequest().authenticated())
////            .formLogin(login -> login
////                    .loginPage("/login")
////                    .loginProcessingUrl("/login")
////                    .usernameParameter("username")
////                    .passwordParameter("password")
////                    .defaultSuccessUrl("/admin/admin1", true)
////                    .failureUrl("/login?error=true"))
////            .logout(logout -> logout
////                    .logoutUrl("/logout")
////                    .logoutSuccessUrl("/login?logout=true"));
////    return httpSecurity.build();
////}
//
//
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/admin/**").permitAll() // Cho phép truy cập vào các URL bắt đầu bằng /admin/**
//                        .anyRequest().permitAll() // Cho phép tất cả các yêu cầu khác
//                )
//                .formLogin(login -> login.disable()) // Vô hiệu hóa trang đăng nhập
//                .logout(logout -> logout.disable()); // Vô hiệu hóa chức năng đăng xuất
//
//        return http.build();
//    }
//
////    @Bean
////    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
////        httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) -> auth
////                        .requestMatchers("/*").permitAll()
////                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN").anyRequest().authenticated())
////        .formLogin(login->login.loginPage("/login").loginProcessingUrl("/login")
////                .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/admin/admin1",true));
//////                .logout(logout->logout.logoutUrl("/logout_admin").logoutSuccessUrl("/login"))
//////                .logout(logout->logout.logoutUrl("/logout_admin").logoutSuccessUrl("/login"));
////        return httpSecurity.build();
////    }
//
//    @Bean
//    WebSecurityCustomizer webSecurityCustomizer(){
//        return (Web)->Web.ignoring().requestMatchers("/static/**","/fe/**","assets/**");
//    }
//}
