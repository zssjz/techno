package com.jason.user.service.impl;

import com.jason.TechnoApplication;
import com.jason.user.service.UserService;
import com.jason.module.MessageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by BNC on 2019/7/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TechnoApplication.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void modifyUser() throws Exception {
    }

    @Test
    public void deleteUser() throws Exception {
    }

    @Test
    public void queryUser() throws Exception {
        MessageDTO msg = userService.queryUser("");
    }

    @Test
    public void queryUserList() throws Exception {
    }

}