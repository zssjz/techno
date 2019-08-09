package com.jason.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by BNC on 2019/8/8.
 */
public class ValidateImageCodeException extends AuthenticationException {

    public ValidateImageCodeException(String msg) {
        super(msg);
    }
}
