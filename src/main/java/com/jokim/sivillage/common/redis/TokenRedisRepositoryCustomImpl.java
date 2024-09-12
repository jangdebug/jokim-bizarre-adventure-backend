package com.jokim.sivillage.common.redis;

import java.time.Duration;
import org.springframework.data.redis.core.RedisTemplate;

public class TokenRedisRepositoryCustomImpl implements TokenRedisRepositoryCustom {

    private final RedisTemplate<String, TokenRedis> redisTemplate;

    public TokenRedisRepositoryCustomImpl(RedisTemplate<String, TokenRedis> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // redis에서 자동으로 토큰 만료 시켜주는 코드. 필요없으면 redis에 뒤에 custom이라 붙은 파일 그냥 삭제하면 됨
    @Override
    public void saveTokenWithExpiration(TokenRedis tokenRedis, long timeoutInSeconds) {
        redisTemplate.opsForValue()
            .set(tokenRedis.getId(), tokenRedis, Duration.ofSeconds(timeoutInSeconds));
    }
}