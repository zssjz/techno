package com.jason.security;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * session并发管理策略
 */
public class ExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
//        sessionInformationExpiredEvent.getResponse().setCharacterEncoding("UTF-8");
        sessionInformationExpiredEvent.getResponse().setContentType("application/json;charset=UTF-8");
        sessionInformationExpiredEvent.getResponse().getWriter().write("并发登录");
    }
}
