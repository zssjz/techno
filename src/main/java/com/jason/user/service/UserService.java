package com.jason.user.service;

import com.jason.user.model.UserDO;
import com.jason.module.MessageDTO;

/**
 * Created by BNC on 2019/7/10.
 */
public interface UserService {

    /**
     * 新增编辑用户信息
     * @param userDO
     * @return
     */
    MessageDTO modifyUser(UserDO userDO);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    MessageDTO deleteUser(String userId);

    /**
     * 查询用户
     * @param userId
     * @return
     */
    MessageDTO queryUser(String userId);

    /**
     * 查询用户
     * @param userDO
     * @return
     */
    MessageDTO queryUserList(UserDO userDO);

    /**
     * 测试所有用户
     * @return
     */
    MessageDTO queryUserList();
}
