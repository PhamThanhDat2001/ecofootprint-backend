package com.doan.ecofootprint_be.security;

import com.doan.ecofootprint_be.config.AuthenticationConfig;
import com.doan.ecofootprint_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationConfig authenticationConfig;
    private final UserService userService;


    private final JwtTokenProvider tokenProvider;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(tokenProvider, userService);
    }
    @Bean
    public PasswordEncoder passwordEncoderAuth() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(authenticationConfig.passwordEncoder());
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoderAuth());
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/v1/login").permitAll()
//                .antMatchers("/api/v1/user/**").hasRole("User")
//                .antMatchers("/api/v1/admin/**").hasRole("Admin")
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .csrf()
//                .disable();
        http
                .cors() // Ngăn chặn request từ một domain khác
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/login").permitAll()
                .antMatchers("/api/v1/user/activeUser").permitAll()
                .antMatchers("/api/v1/user/register").permitAll()
                .antMatchers("/api/v1/user/userRegistrationConfirmRequest/**").permitAll()
//                .antMatchers("/api/v1/forgot_password").permitAll()
//                .antMatchers("/api/v1/change_password/**").permitAll()
                .antMatchers("/api/v1/user/**").hasRole("USER")
//                .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
