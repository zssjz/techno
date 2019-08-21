package com.jason.security;

import com.jason.module.MessageDTO;
import com.jason.security.handler.IdentificationFailureHandler;
import com.jason.security.model.ValidateCode;
import com.jason.security.model.ValidateImageCode;
import com.jason.security.support.ValidateCodeGenerator;
import com.jason.security.support.ValidateCodeSender;
import com.jason.security.support.ValidateImageCodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BNC on 2019/8/7.
 */
@RestController
public class SecurityController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String SESSION_KEY = "VALIDATE_IMAGE_CODE";

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateImageCodeGenerator validateImageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator validateCodeGenerator;

    @Autowired
    private ValidateCodeSender validateCodeSender;

    /**
     * 需要身份认证时处理前对html请求及接口进行分流
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public MessageDTO requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("请求路径为：{}", targetUrl);
            if (targetUrl.endsWith(".html")) {
                // 判断请求后缀为html是重定向到指定路径
                redirectStrategy.sendRedirect(request, response, "/login");
            }
        }
        return new MessageDTO(0, HttpStatus.UNAUTHORIZED, "需要身份认证");
    }

    /**
     * 生成图片验证码
     * @param request
     * @param response
     */
    @GetMapping("/code/image")
    public void createValidateImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ValidateImageCode imageCode = validateImageCodeGenerator.imageGenerator(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    /**
     * 发送短信验证码
     * @param request
     * @param response
     */
    @GetMapping("/code/sms")
    public void createvalidateSmsCode(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        if (StringUtils.isEmpty(phone)) {
            // TODO
        }
        ValidateCode validateCode = validateCodeGenerator.smsGenerator(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, validateCode);
        validateCodeSender.send(phone, validateCode);

    }
}
