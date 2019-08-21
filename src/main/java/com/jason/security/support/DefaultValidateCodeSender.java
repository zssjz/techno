package com.jason.security.support;

import com.jason.security.model.ValidateCode;

/**
 * 默认短信发送实现
 * Created by BNC on 2019/8/19.
 */
public class DefaultValidateCodeSender implements ValidateCodeSender {

    @Override
    public void send(String phone, ValidateCode code) {
        // TODO 接入短信服务商
        System.out.println(phone);
        System.out.println(code.getCode());
    }
}
