package com.jason.security.control;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "验证码")
@RestController
@RequestMapping("/validate")
public class ValidateController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "短信验证", notes = "短信验证码")
    @GetMapping("/sms")
    public void smsValidate() {

    }

    @ApiOperation(value = "图形验证", notes = "图形验证码")
    @GetMapping("/img")
    public void imgValidate() {

    }
}
