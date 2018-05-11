package com.jason.components.service;

import com.jason.entity.UserDO;

/**
 * Created by Jason on 2018/4/28.
 */
public interface RedisService {

    /**
     * 读取对象
     * @param key
     * @return
     */
    UserDO getValue(String key);

    /**
     * 存入对象
     * @param user
     * @return
     */
    String setValue(UserDO user);
}
