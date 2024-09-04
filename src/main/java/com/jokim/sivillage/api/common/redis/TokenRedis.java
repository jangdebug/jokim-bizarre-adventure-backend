package com.jokim.sivillage.api.common.redis;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Setter
@NoArgsConstructor
@RedisHash(value = "token", timeToLive = 60 * 60 * 24 * 7) // 리프레시토큰과 expiretime 일치
public class TokenRedis {

    @Id
    private String id;  // 일반적으로 username 또는 customer UUID를 사용
    private String accessToken;
    private String customerUuid;
    private String refreshToken;

    public TokenRedis(String id, String accessToken, String refreshToken) {
        this.customerUuid = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
