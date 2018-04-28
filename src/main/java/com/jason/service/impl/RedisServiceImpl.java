package com.jason.service.impl;

import com.jason.entity.UserDT;
import com.jason.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by BNC on 2018/4/28.
 */
@Service
public class RedisServiceImpl implements RedisService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserDT getValue(String key) {
        return null;
    }

    @Override
    public String setValue(UserDT user) {
        return null;
    }
}
