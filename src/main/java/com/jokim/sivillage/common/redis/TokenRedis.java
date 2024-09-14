package com.jokim.sivillage.common.redis;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@NoArgsConstructor
@RedisHash(value = "token", timeToLive = 60 * 60 * 24 * 7) // 토큰의 만료 시간 설정 (7일)
public class TokenRedis {

    @Id
    private String id;
    private String refreshToken;

    public TokenRedis(String customerUuid, String refreshToken) {
        this.id = customerUuid; // UUID
        this.refreshToken = refreshToken;
    }
}
