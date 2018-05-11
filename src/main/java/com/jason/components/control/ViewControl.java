package com.jason.components.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by Jason on 2018/4/19.
 */
@ApiIgnore
@Controller
public class ViewControl {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
