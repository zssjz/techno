package com.jason.service.impl;

import com.jason.entity.UserDT;
import com.jason.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Redis集成
 * Created by BNC on 2018/4/28.
 */
@Service
public class RedisServiceImpl implements RedisService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // StringRedisTemplate（继承了RedisTemplate） 默认提供的Redis接口，适合Key和Value都是字符串的情况
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // RedisTemplate 另外一个内置的API
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserDT getValue(String key) {
        // 设置普通的key-value
        stringRedisTemplate.opsForValue().set("key", "value");
        stringRedisTemplate.opsForValue().get("key");
        // List
        stringRedisTemplate.opsForList().leftPush("key", "value");
        Long size = stringRedisTemplate.opsForList().size("key");
        List list = stringRedisTemplate.opsForList().range("key", 0, size - 1);
        // map
        stringRedisTemplate.opsForHash().put("map", "key", "value");
        stringRedisTemplate.opsForHash().putAll("map", new HashMap<>());
        Object obj = stringRedisTemplate.opsForHash().get("map", "key");
        return null;
    }

    @Override
    public String setValue(UserDT user) {

        return null;
    }
}
