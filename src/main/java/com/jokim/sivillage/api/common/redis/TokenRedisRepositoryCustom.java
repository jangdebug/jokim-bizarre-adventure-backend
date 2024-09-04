package com.jokim.sivillage.api.common.redis;

public interface TokenRedisRepositoryCustom {

    void saveTokenWithExpiration(TokenRedis tokenRedis, long timeoutInSeconds);
}
