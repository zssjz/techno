package com.jason.config.dao;

import com.jason.TechnoApplication;
import com.jason.dao.UserRepository;
import com.jason.entity.UserDT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by BNC on 2018/4/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TechnoApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        UserDT userDT = new UserDT("jason","admin",1,1,1,1);
        userRepository.save(userDT);
        System.out.println(userDT);

    }

}