package com.jason.security.service.impl;

import com.jason.security.model.ValidateCode;
import com.jason.security.service.ValidateCodeGenerator;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

@Service
public class ValidateCodeImpl implements ValidateCodeGenerator {

    @Override
    public ValidateCode generator(ServletWebRequest request) {
        return null;
    }
}
