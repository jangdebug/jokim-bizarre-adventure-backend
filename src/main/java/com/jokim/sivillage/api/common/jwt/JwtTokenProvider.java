package com.jokim.sivillage.api.common.jwt;

import com.jokim.sivillage.api.customer.domain.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class JwtTokenProvider {

    private final Environment env;

    //처음 로그인시 만들어지는 accessToken
    public String generateAccessToken(Authentication authentication) {
        Claims claims = Jwts.claims().subject(authentication.getName()).build();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + env.getProperty("jwt.access-expire-time", Long.class).longValue());
        // Customer 객체에서 UUID 가져오기
        String customerUuid = "";
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            // 유저 정보 가져오기, Customer는 UserDetails를 구현한다고 가정
            if (userDetails instanceof Customer) {
                customerUuid = ((Customer) userDetails).getCustomerUuid();
            }
        }

        return Jwts.builder()
                .signWith(getSignKey())
                //.claim("email", claims.getSubject())
                .claim("uuid", customerUuid) // 여기서 UUID를 클레임에 추가
                .issuedAt(now)
                .expiration(expiration)
                .compact();
    }

    //refresh토큰 생성
    public String generateRefreshToken(Authentication authentication) {
        Claims claims = Jwts.claims().subject(authentication.getName()).build();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + env.getProperty("jwt.refresh-expire-time", Long.class).longValue());

        return Jwts.builder()
                .signWith(getSignKey())
               // .claim("email", claims.getSubject())
               // .issuedAt(now)
                .expiration(expiration)
                .compact();
    }

    public Key getSignKey() {
        return Keys.hmacShaKeyFor( env.getProperty("jwt.secret-key").getBytes() );
    }

}