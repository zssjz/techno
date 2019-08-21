package com.jason.security.support;

import com.jason.security.model.ValidateCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Random;

/**
 * 默认验证码实现类
 * Created by BNC on 2019/8/9.
 */
public class DefaultValidateCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ValidateCode smsGenerator(ServletWebRequest request) {
        Random random = new Random();
        String code = String.valueOf(random.nextInt(1000000));
        return new ValidateCode(code, 60);
    }

}
