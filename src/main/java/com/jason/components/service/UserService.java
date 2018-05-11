package com.jason.components.service;

import com.jason.dto.MessageDTO;
import com.jason.entity.UserDO;

/**
 * Created by Jason on 2018/4/18.
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     * @return
     */
    MessageDTO registerUser(UserDO user);
}
