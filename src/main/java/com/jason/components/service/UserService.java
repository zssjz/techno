package com.jason.components.service;

import com.jason.dto.MessageDTO;
import com.jason.entity.UserDO;
import org.springframework.data.domain.Pageable;

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

    /**
     *
     * @return
     */
    MessageDTO getUserAll(Pageable pageable);

    /**
     *
     * @param username
     * @return
     */
    MessageDTO getUserByUsername(String username);

    /**
     *
     * @param userDO
     * @return
     */
    MessageDTO updateUser(UserDO userDO);

    /**
     *
     * @param username
     * @return
     */
    MessageDTO deleteUser(String username);
}
