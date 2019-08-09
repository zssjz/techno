package com.jason.security.model;

import java.time.LocalDateTime;

/**
 * 验证码
 * Created by BNC on 2019/8/9.
 */
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;
    
    private boolean expired;

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
        this.expired = LocalDateTime.now().isAfter(expireTime);
    }

    public ValidateCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public void setExpired(boolean expired) {
        this.expired = LocalDateTime.now().isAfter(expireTime);
    }
}
