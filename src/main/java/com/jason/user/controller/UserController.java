package com.jason.user.controller;

import com.jason.user.model.UserDO;
import com.jason.user.service.UserService;
import com.jason.module.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by BNC on 2019/7/9.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/modify")
    public MessageDTO modifyUser(@Validated UserDO userDO, BindingResult result) {
        if (result.hasErrors()) {
            StringBuffer stringBuffer = new StringBuffer();
            result.getAllErrors().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                stringBuffer.append(fieldError.getDefaultMessage());
//                stringBuffer.append(error.getDefaultMessage());
            });
            return new MessageDTO(0, HttpStatus.BAD_REQUEST, new String(stringBuffer));
        }
        // TODO
        return null;
    }

    @GetMapping("/{id}/info")
    public MessageDTO findUser(@PathVariable("id") String userId) {
        return userService.queryUser(userId);
    }

    @GetMapping("/all")
    public MessageDTO findUser() {
        return userService.queryUserList();
    }

    /**
     * 获取当前认证信息
     * @param authentication
     * @return
     */
    @GetMapping("/current")
    public Object getCurrentUser(Authentication authentication) {
//        return SecurityContextHolder.getContext().getAuthentication();
        return  authentication;
    }

    /**
     * 获取当前用户信息
     * @param userDetails
     * @return
     */
    @GetMapping("/currentUser")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }


    @PostMapping("/signup")
    public MessageDTO sigUp() {

        return null;
    }

}
