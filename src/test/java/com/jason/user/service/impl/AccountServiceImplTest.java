package com.jason.user.service.impl;

import com.jason.TechnoApplication;
import com.jason.user.model.AccountDO;
import com.jason.user.model.UserDO;
import com.jason.user.model.enums.GenderEnum;
import com.jason.user.service.AccountService;
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
public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void modifyAccount() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setNickname("clark");
        userDO.setAge(20);
        userDO.setGender(GenderEnum.FEMALE);
        userDO.setCreateTime(System.currentTimeMillis());

        AccountDO accountDO = new AccountDO();
        accountDO.setUsername("username");
        accountDO.setPassword("password");
        accountDO.setAuthorities("admin");

        userDO.setAccountDO(accountDO);
        accountDO.setUserDO(userDO);

        MessageDTO msg = accountService.modifyAccount(accountDO);
        System.out.println(msg);
    }

    @Test
    public void deleteAccount() throws Exception {
        String accountId = "2c90c0886bdb4815016bdb482ec00000";
        MessageDTO msg = accountService.deleteAccount(accountId);
        System.out.println(msg);
    }

    @Test
    public void queryAccount() throws Exception {
        String accountId = "2c90c0886bdb23f0016bdb2410690000";
        MessageDTO msg = accountService.queryAccount(accountId);
        System.out.println(msg);
    }

    @Test
    public void queryAccountList() throws Exception {
    }

}