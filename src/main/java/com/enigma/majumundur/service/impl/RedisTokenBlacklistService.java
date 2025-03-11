package com.enigma.majumundur.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisTokenBlacklistService {
    private final StringRedisTemplate stringRedisTemplate;

    public void blackListToken(String token, Long expirationTime) {
        stringRedisTemplate.opsForValue().set(token, "blacklisted", expirationTime, TimeUnit.MILLISECONDS);
    }

    public Boolean isTokenBlacklisted(String token) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(token));
    }
}
