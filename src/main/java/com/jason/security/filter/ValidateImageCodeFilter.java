package com.jason.security.filter;

import com.jason.exception.ValidateImageCodeException;
import com.jason.security.SecurityController;
import com.jason.security.handler.IdentificationFailureHandler;
import com.jason.security.model.ValidateImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.jason.security.SecurityController.SESSION_KEY;

/**
 * 图形验证码校验过滤器
 * Created by BNC on 2019/8/8.
 */
@Component
public class ValidateImageCodeFilter extends OncePerRequestFilter {

    @Autowired
    private IdentificationFailureHandler identificationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String url = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        if (!StringUtils.isEmpty(contextPath) && url.startsWith(contextPath)) {
            url = url.substring(contextPath.length());
        }

        if ("/form/login".equals(url) && "POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            try {
                validate(new ServletWebRequest(httpServletRequest));
                logger.info("验证码校验通过");
            } catch (ValidateImageCodeException e) {
                logger.info("验证码校验不通过");
                identificationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 图片验证码校验方法
     * @param servletWebRequest
     */
    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        ValidateImageCode imageCode = (ValidateImageCode) sessionStrategy.getAttribute(servletWebRequest, SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");

        if (StringUtils.isEmpty(codeInRequest)) {
            throw new ValidateImageCodeException("验证码不能为空");
        }
        if (imageCode.isExpired()) {
            sessionStrategy.removeAttribute(servletWebRequest, SESSION_KEY);
            throw new ValidateImageCodeException("验证码已过期");
        }
        if (!imageCode.getCode().equals(codeInRequest)) {
            throw new ValidateImageCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(servletWebRequest, SESSION_KEY);
    }
}
