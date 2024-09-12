package com.jokim.sivillage.common.redis;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@NoArgsConstructor
@RedisHash(value = "token", timeToLive = 60 * 60 * 24 * 7) // 토큰의 만료 시간 설정 (7일)
public class TokenRedis {

    @Id
    private String id;  // 이 필드를 Redis 키로 사용
    private String refreshToken;

    public TokenRedis(String customerUuid, String refreshToken) {
        this.id = customerUuid; // UUID가 Redis 키로 사용됨
        this.refreshToken = refreshToken;
    }
}
