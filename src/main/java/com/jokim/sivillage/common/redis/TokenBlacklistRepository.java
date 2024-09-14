package com.jokim.sivillage.common.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class TokenBlacklistRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public TokenBlacklistRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 블랙리스트에 Access Token 저장
    public void saveBlacklistedToken(String token, long duration, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(token, "blacklisted", duration, timeUnit);
    }

    // 블랙리스트 확인
    public boolean isBlacklisted(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(token));
    }

}
