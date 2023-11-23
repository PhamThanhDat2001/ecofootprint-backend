package com.doan.ecofootprint_be.security;

import com.doan.ecofootprint_be.config.JwtConfig;
import com.doan.ecofootprint_be.entity.CustomUserDetail;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
    @Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
//    private final String JWT_SECRET = "mockProject";
//
//    private final long JWT_EXPIRATION = 604800000L;
    private final JwtConfig jwtConfig;
    public String generateToken(CustomUserDetail userDetail) {
        System.out.println(userDetail.getUsers().getId());
        return Jwts.builder().setId(UUID.randomUUID().toString())
                .setSubject(String.valueOf(userDetail.getUsers().getId()))
                .setIssuedAt(new Date())
//                .setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION))
//                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .setExpiration(new Date(new Date().getTime() + jwtConfig.getJwtExpiration()))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getJwtSecret())
                .compact();
    }

    public Integer getAccountIdFromJwt(String jwt) {
        Claims claims = Jwts.parser()
//                .setSigningKey(JWT_SECRET)
                .setSigningKey(jwtConfig.getJwtSecret())
                .parseClaimsJws(jwt)
                .getBody();
        return Integer.parseInt(claims.getSubject());
    }

    public boolean validateToken (String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtConfig.getJwtSecret()).parseClaimsJws(authToken);
//            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        }catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        }catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        }catch (UnsupportedJwtException ex) {
            log.error("Unsupport JWT token");
        }catch (IllegalArgumentException ex) {
            log.error("JWt claims string is empty");
        }
        return false;
    }
}
