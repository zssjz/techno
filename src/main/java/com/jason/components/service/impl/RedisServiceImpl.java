package com.jason.components.service.impl;

import com.jason.entity.UserDO;
import com.jason.components.service.RedisService;
import com.jason.utils.RedisUtil;
import com.jason.utils.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by Jason on 2018/4/28.
 */
@Service
public class RedisServiceImpl implements RedisService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redis;

    @Override
    public UserDO getValue(String key) {
        String data = (String) redis.get(key);
        UserDO user = SerializeUtil.deserialize(data.getBytes(), UserDO.class);
        return user;
    }

    @Override
    public String setValue(UserDO user) {
        byte[] bytes = SerializeUtil.serialize(user);
        return new String(bytes);
    }
}
