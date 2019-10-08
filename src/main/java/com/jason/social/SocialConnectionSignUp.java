package com.jason.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

@Component
public class SocialConnectionSignUp implements ConnectionSignUp {

    /**
     * 根据社交账号默认信息创建用户并返回用户唯一值
     * @param connection
     * @return
     */
    @Override
    public String execute(Connection<?> connection) {
        return connection.getKey().getProviderUserId();
    }
}
