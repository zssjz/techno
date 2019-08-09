package com.jason.security.support;

import com.jason.security.model.ValidateCode;
import com.jason.security.model.ValidateImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图片验证码接口，可自定义实现类来替换默认实现方法
 * Created by BNC on 2019/8/9.
 */
public interface ValidateImageCodeGenerator {

    /**
     * 图片验证码生成器
     * @param request
     * @return
     */
    ValidateImageCode imageGenerator(ServletWebRequest request);
}
