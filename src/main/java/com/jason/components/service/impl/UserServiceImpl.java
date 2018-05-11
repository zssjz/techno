package com.jason.components.service.impl;

import com.jason.components.dao.UserRepository;
import com.jason.dto.MessageDTO;
import com.jason.entity.UserDO;
import com.jason.components.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
            msg.setState(0);
            msg.setCode("");
            msg.setInfo("注册异常");
            logger.error("注册失败：{}", e.getMessage());
            e.printStackTrace();
            return msg;
        }
        if (result != null) {
            msg.setState(1);
            msg.setCode("");
            msg.setContent("注册成功");
        } else {
            msg.setState(0);
            msg.setCode("");
            msg.setInfo("注册失败");
        }
        return msg;
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
