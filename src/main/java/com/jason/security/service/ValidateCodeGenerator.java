package com.jason.security.service;

import com.jason.security.model.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;


public interface ValidateCodeGenerator {

    /**
     * 生成验证码
     * @param request
     * @return
     */
    ValidateCode generator(ServletWebRequest request);
}
