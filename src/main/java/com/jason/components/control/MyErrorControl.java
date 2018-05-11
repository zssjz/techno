package com.jason.components.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by Jason on 2018/4/18.
 */
@ApiIgnore
@Controller
public class MyErrorControl implements ErrorController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getErrorPath() {
        return null;
    }

}
