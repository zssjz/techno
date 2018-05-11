package com.jason.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 一般来说，LogoutHandler的实现类可以参阅到注销处理中。他们被用来执行必要的清理，因而他们不应该抛出错误
 * Created by Jason on 2018/4/18.
 */
@Component
public class MyLogoutHandler implements LogoutHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        try {
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        } catch (Exception e) {
            logger.error("服务器异常：{}", e.getMessage());
            e.printStackTrace();
        }
    }
}
