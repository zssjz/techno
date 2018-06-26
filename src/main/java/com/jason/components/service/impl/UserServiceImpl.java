package com.jason.components.service.impl;

import com.jason.repository.UserRepository;
import com.jason.dto.MessageDTO;
import com.jason.entity.UserDO;
import com.jason.components.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by Jason on 2018/4/18.
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public MessageDTO registerUser(UserDO user) {
        MessageDTO msg = new MessageDTO();
        String password = user.getPassword();
        user.setPassword(this.encryptPassword(password.trim()));
        UserDO result = null;
        try {
            result = userRepository.save(user);
        } catch (Exception e) {
            msg.setState(HttpStatus.INTERNAL_SERVER_ERROR.value());
            msg.setCode(0);
            msg.setInfo(e.getMessage());
            logger.error("服务器异常，注册失败：{}", e.getMessage());
            e.printStackTrace();
            return msg;
        }
        if (result != null) {
            msg.setState(1);
            msg.setCode(HttpStatus.OK.value());
            msg.setContent("注册成功");
        } else {
            msg.setState(0);
            msg.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            msg.setInfo("注册失败");
        }
        return msg;
    }

    @Override
    public MessageDTO getUserAll(Pageable pageable) {
        MessageDTO msg = new MessageDTO();
        try {
            List<UserDO> result = userRepository.findAll();
            msg.setState(HttpStatus.OK.value());
            msg.setCode(1);
            msg.setContent(result);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setState(HttpStatus.INTERNAL_SERVER_ERROR.value());
            msg.setCode(0);
            msg.setContent(e.getMessage());
        }
        return msg;
    }

    @Override
    public MessageDTO getUserByUsername(String username) {
        MessageDTO msg = new MessageDTO();
        try {
            UserDO user = userRepository.findUserDTByUsername(username);
            msg.setState(HttpStatus.OK.value());
            msg.setCode(1);
            msg.setContent(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            msg.setState(HttpStatus.INTERNAL_SERVER_ERROR.value());
            msg.setCode(0);
            msg.setInfo(e.getMessage());
        }
        return msg;
    }

    @Override
    public MessageDTO updateUser(UserDO userDO) {
        MessageDTO msg = new MessageDTO();
        try {
            // TODO
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return msg;
    }

    @Override
    public MessageDTO deleteUser(String username) {
        return null;
    }

    /**
     * 加密
     * @param password
     * @return
     */
    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
