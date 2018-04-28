package com.jason.control;

import com.jason.dto.MessageDTO;
import com.jason.entity.UserDT;
import com.jason.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by BNC on 2018/4/17.
 */
@Api(value = "公共组件")
@RestController
public class SystemControl {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 获取系统时间
     * @return
     */
    @ApiIgnore
    @GetMapping("/time")
    public Long getSystemTime() {
        return System.currentTimeMillis();
    }

    /**
     * 注册
     * @param userDT
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "公共注册")
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
