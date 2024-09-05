package com.jokim.sivillage.common.redis;

public interface TokenRedisRepositoryCustom {

    void saveTokenWithExpiration(TokenRedis tokenRedis, long timeoutInSeconds);
}
