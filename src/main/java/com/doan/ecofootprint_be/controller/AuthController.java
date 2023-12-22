package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.dto.JwtResponse;
import com.doan.ecofootprint_be.entity.BodyLogin;
import com.doan.ecofootprint_be.entity.CustomUserDetail;
import com.doan.ecofootprint_be.entity.Users;
import com.doan.ecofootprint_be.security.JwtTokenProvider;
import com.doan.ecofootprint_be.service.IUserService;
import com.doan.ecofootprint_be.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    // interface dc sd de qly qua trinh xác thực
    private final AuthenticationManager authenticationManager;

    private final IUserService userService;

    private final JwtTokenProvider tokenProvider;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody BodyLogin bodyLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(bodyLogin.getUsername(), bodyLogin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
        Users user = userService.getAccountByUsername(bodyLogin.getUsername());

        JwtResponse jwtResponse = new JwtResponse(jwt, user);

        return ResponseEntity.ok(jwtResponse);
    }
}
//    @PostMapping("/auth/login")
////    public String authenticateUser(@Valid @RequestBody BodyLogin BodyLogin) {
//
//        // . authenticate để thuực hiện quá trình xác thực  Phương thức này sẽ xác minh thông tin
//        // đăng nhập được cung cấp bằng cách sử dụng các Provider xác thực có sẵn
//        Authentication authentication = authenticationManager.authenticate(
//                // khởi tạo obj
//                new UsernamePasswordAuthenticationToken(BodyLogin.getUsername(), BodyLogin.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
////        Users users = userService.getAccountByUsername(BodyLogin.getUsername());
//        // được sử dụng để sinh ra một JSON Web Token (JWT) sau khi người dùng đã được xác
//        // thực thành công. Dưới đây là một giải thích chi tiết:
//        String jwt = tokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
////        return jwt;
//
//    }
//}