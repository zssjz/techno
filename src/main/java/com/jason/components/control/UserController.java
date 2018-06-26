package com.jason.components.control;

import com.jason.components.service.UserService;
import com.jason.dto.MessageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "账号管理组件")
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户列表（分页）", notes = "无条件筛选")
    @GetMapping("/list")
    public MessageDTO getUserList(@RequestParam("page") Integer page,
                                  @RequestParam("size") Integer size) {
        MessageDTO msg = null;
        try {
            Pageable pageable = new PageRequest(page, size);
            msg = userService.getUserAll(pageable);
        } catch (Exception e) {
            logger.error(e.getMessage());
            msg = new MessageDTO();
            msg.setCode(0);
            msg.setState(HttpStatus.INTERNAL_SERVER_ERROR.value());
            msg.setInfo(e.getMessage());
        }
        return msg;
    }

    @ApiOperation(value = "获取目标用户详情", notes = "无条件筛选")
    @GetMapping("/{username}/detail")
    public MessageDTO getUserByUsername(@PathVariable String username) {
        MessageDTO msg = null;
        try {
            msg = userService.getUserByUsername(username);
        } catch (Exception e) {
            logger.error(e.getMessage());
            msg = new MessageDTO();
            msg.setState(HttpStatus.INTERNAL_SERVER_ERROR.value());
            msg.setCode(0);
            msg.setInfo(e.getMessage());
        }
        return msg;
    }

}
