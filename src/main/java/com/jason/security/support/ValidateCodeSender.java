package com.jason.security.support;

import com.jason.security.model.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by BNC on 2019/8/9.
 */
public interface ValidateCodeSender {

    /**
     * 发送短信验证码
     * @param phone
     * @param code
     */
    void send(String phone, ValidateCode code);
}
