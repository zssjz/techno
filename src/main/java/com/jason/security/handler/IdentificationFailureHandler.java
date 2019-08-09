package com.jason.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.module.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证失败处理器 实现 AuthenticationFailureHandler
 * 或继承默认的失败处理器SimpleUrlAuthenticationFailureHandler，其方法的super(request, response, e)将会重定向页面
 * Created by BNC on 2019/8/8.
 */
@Component
public class IdentificationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    private ObjectMapper objectMapper;

//    @Override
//    public void onAuthenticationFailure(
//            HttpServletRequest httpServletRequest,
//            HttpServletResponse httpServletResponse,
//            AuthenticationException e) throws IOException, ServletException {
//        logger.debug("认证失败");
//        // TODO 其他处理
//        httpServletResponse.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(e));
//    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.debug("认证失败");
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            if (targetUrl.endsWith(".html")) {
                super.onAuthenticationFailure(request, response, exception);
                return;
            }
        }
        response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new MessageDTO(0, HttpStatus.NON_AUTHORITATIVE_INFORMATION, exception.getMessage())));
    }
}
