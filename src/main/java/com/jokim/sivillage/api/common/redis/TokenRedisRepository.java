package com.jokim.sivillage.api.common.redis;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRedisRepository extends CrudRepository<TokenRedis,String> {
    TokenRedis findByAccessToken(String accessToken); // AccessToken으로 찾아내기
}
