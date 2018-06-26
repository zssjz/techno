package com.jason.config.dao;

import com.jason.TechnoApplication;
import com.jason.repository.UserRepository;
import com.jason.entity.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Jason on 2018/4/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TechnoApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        UserDO userDO = new UserDO("jason@163.com","admin",1,1,1,1);
        UserDO result = userRepository.save(userDO);
        System.out.println(result);

    }

}