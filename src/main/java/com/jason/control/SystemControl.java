package com.jason.control;

import com.jason.dto.MessageDTO;
import com.jason.entity.UserDT;
import com.jason.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BNC on 2018/4/17.
 */
@RestController
public class SystemControl {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     *
     * @return
     */
    @GetMapping("/time")
    public Long getSystemTime() {
        return System.currentTimeMillis();
    }

    /**
     * 注册
     * @param userDT
     * @return
     */
    @PostMapping("/register")
    public MessageDTO register(@Validated UserDT userDT) {
        MessageDTO msg = null;
        try {
            msg = userService.registerUser(userDT);
        } catch (Exception e) {
            logger.error("服务器异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return msg;
    }


}
