package com.jason.service;

import com.jason.dto.MessageDTO;
import com.jason.entity.UserDT;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by BNC on 2018/4/18.
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     * @return
     */
    MessageDTO registerUser(UserDT user);
}
