package com.jason.security.support;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by BNC on 2019/8/9.
 */
public interface ValidateCodeProcessor {

    /**
     * 生成验证码
     * @param request
     * @throws Exception
     */
    void createValidateCode(ServletWebRequest request) throws Exception;
}
