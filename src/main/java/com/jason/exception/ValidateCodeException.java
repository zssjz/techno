package com.jason.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常
 * Created by BNC on 2019/8/8.
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
