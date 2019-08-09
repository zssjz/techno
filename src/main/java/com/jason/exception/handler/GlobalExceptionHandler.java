package com.jason.exception.handler;

import com.jason.module.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常拦截
 * Created by BNC on 2019/7/10.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = {RuntimeException.class})
    public MessageDTO handleException(Exception e) {
        logger.error("拦截异常：{}", e.getMessage());
        return new MessageDTO(0, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
