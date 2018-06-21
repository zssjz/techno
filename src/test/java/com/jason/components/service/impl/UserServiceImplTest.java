package com.jason.components.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.TechnoApplication;
import com.jason.components.service.UserService;
import com.jason.dto.MessageDTO;
import com.jason.entity.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TechnoApplication.class)
public class UserServiceImplTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserService userService;

    @Test
    public void registerUser() {
        UserDO user = new UserDO("zssjz@126.com", "jason", 1, 1, 1, 1);
        System.out.println(userService.registerUser(user));
    }

    @Test
    public void getUserAll() {
        Pageable pageable = new PageRequest(1,10);
        MessageDTO msg = userService.getUserAll(pageable);
        try {
            System.out.println(mapper.writeValueAsString(msg));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}