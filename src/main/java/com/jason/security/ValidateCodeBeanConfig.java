package com.jason.security;

import com.jason.security.support.DefaultValidateCodeGenerator;
import com.jason.security.support.ValidateCodeGenerator;
import com.jason.security.support.ValidateImageCodeGenerator;
import com.jason.security.support.DefaultValidateImageCodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置项
 * Created by BNC on 2019/8/9.
 */
@Configuration
public class ValidateCodeBeanConfig {

    /**
     * 图片验证码配置
     * 当容器中不存在validateImageCodeGenerator这个bean时，则使用默认的图形验证码实现
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "validateImageCodeGenerator")
    public ValidateImageCodeGenerator validateImageCodeGenerator() {
        DefaultValidateImageCodeGenerator generator = new DefaultValidateImageCodeGenerator();
        return generator;
    }

    /**
     * 验证码配置
     * 当容器中不存在ValidateCodeGenerator的实现类时，则使用默认的验证码实现类
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(ValidateCodeGenerator.class)
    public ValidateCodeGenerator validateCodeGenerator() {
        DefaultValidateCodeGenerator generator = new DefaultValidateCodeGenerator();
        return generator;
    }
}
