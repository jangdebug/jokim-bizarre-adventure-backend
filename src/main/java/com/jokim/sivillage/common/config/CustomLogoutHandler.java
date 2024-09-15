package com.jokim.sivillage.common.config;

import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import com.jokim.sivillage.common.redis.TokenBlacklistRepository;
import com.jokim.sivillage.common.redis.TokenRedisRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenBlacklistRepository tokenBlacklistRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRedisRepository tokenRedisRepository;

    public CustomLogoutHandler(TokenBlacklistRepository tokenBlacklistRepository,
                               JwtTokenProvider jwtTokenProvider,
                               TokenRedisRepository tokenRedisRepository) {
        this.tokenBlacklistRepository = tokenBlacklistRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenRedisRepository = tokenRedisRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        // Authorization 헤더에서 AccessToken 추출
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.replace("Bearer ", "");
            try {
                // 액세스 토큰에서 UUID 추출 및 유효성 검사
                String uuid = jwtTokenProvider.validateAndGetUserUuid(accessToken);

                // Redis에서 리프레시 토큰 삭제
                tokenRedisRepository.deleteById(uuid);

                // Access Token 블랙리스트에 추가
                Date expirationDate = Jwts.parser()
                    .verifyWith((SecretKey) jwtTokenProvider.getSignKey())
                    .build().parseSignedClaims(accessToken).getPayload().getExpiration();
                long now = System.currentTimeMillis();
                long timeToLive = expirationDate.getTime() - now;

                tokenBlacklistRepository.saveBlacklistedToken(accessToken, timeToLive,
                    TimeUnit.MILLISECONDS);

            }catch (Exception e) {
                // 로그아웃 중 발생한 예외 처리
                throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}