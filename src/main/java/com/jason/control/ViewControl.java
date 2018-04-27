package com.jason.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by BNC on 2018/4/19.
 */
@Controller
public class ViewControl {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
