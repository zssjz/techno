package com.jason.components.dao;

import com.jason.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jason on 2018/4/13.
 */
public interface UserRepository extends JpaRepository<UserDO, String> {

    /**
     * 根据账号查找
     * @param username
     * @return
     */
    UserDO findUserDTByUsername(String username);
}
