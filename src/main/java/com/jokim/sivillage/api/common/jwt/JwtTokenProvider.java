package com.jokim.sivillage.api.common.jwt;

import com.jokim.sivillage.api.customer.domain.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtTokenProvider {

    private final Environment env;

    public String generateAccessToken(Authentication authentication) {
        // JWT 클레임 생성
        Claims claims = Jwts.claims().setSubject(authentication.getName()).build();

        // 현재 시간과 만료 시간 설정
        Date now = new Date();
        long expireTime = env.getProperty("jwt.access-expire-time", Long.class);
        Date expiration = new Date(now.getTime() + expireTime);

        // Authentication 객체에서 Customer UUID 가져오기
        String customerUuid = extractCustomerUuid(authentication);

        // JWT 토큰 생성 및 반환
        return Jwts.builder()
            .setClaims(claims)
            .claim("uuid", customerUuid) // UUID를 클레임에 추가
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(getSignKey())
            .compact();
    }

    //토큰안에 uuid 담기
    private String extractCustomerUuid(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof Customer) { // Customer는 UserDetails를 구현
            return ((Customer) principal).getCustomerUuid();
        }

        return ""; // UUID가 없는 경우 빈 문자열 반환
    }


    //refresh토큰 생성
    public String generateRefreshToken(Authentication authentication) {
        Claims claims = Jwts.claims().subject(authentication.getName()).build();
        Date now = new Date();
        Date expiration = new Date(
            now.getTime() + env.getProperty("jwt.refresh-expire-time", Long.class).longValue());

        return Jwts.builder()
            .signWith(getSignKey())
            // .claim("email", claims.getSubject())
            // .issuedAt(now)
            .expiration(expiration)
            .compact();
    }

    // 새로운 메서드: 토큰에서 클레임 추출
    public Claims getClaimsFromToken(String token) {
        JwtParser parser = Jwts.parser()
            .setSigningKey(getSignKey())
            .build();

        return parser.parseClaimsJws(token).getBody();
    }

    // 토큰에서 UUID 추출
    public String getUuidFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("uuid", String.class);
    }

    public Key getSignKey() {
        return Keys.hmacShaKeyFor(env.getProperty("jwt.secret-key").getBytes());
    }

}