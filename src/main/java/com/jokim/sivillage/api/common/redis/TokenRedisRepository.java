package com.jokim.sivillage.api.common.redis;

import org.springframework.data.repository.CrudRepository;

public interface TokenRedisRepository extends CrudRepository<TokenRedis, String> {

    TokenRedis findByAccessToken(String accessToken); // AccessToken으로 찾아내기
    TokenRedis findByRefreshToken(String refreshToken);
    void deleteByAccessToken(String accessToken);
    void deleteByRefreshToken(String refreshToken);

}
