package com.jason.security.support;

import com.jason.security.model.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码接口，可自定义实现类来替换默认实现
 * Created by BNC on 2019/8/9.
 */
public interface ValidateCodeGenerator {

    /**
     * 验证码生成器
     * @param request
     * @return
     */
    ValidateCode smsGenerator(ServletWebRequest request);
}
