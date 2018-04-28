package com.jason.service;

import com.jason.entity.UserDT;

/**
 * Created by BNC on 2018/4/28.
 */
public interface RedisService {

    /**
     * 读取对象
     * @param key
     * @return
     */
    UserDT getValue(String key);

    /**
     * 存入对象
     * @param user
     * @return
     */
    String setValue(UserDT user);
}
