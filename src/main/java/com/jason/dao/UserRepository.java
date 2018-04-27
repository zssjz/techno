package com.jason.dao;

import com.jason.entity.UserDT;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by BNC on 2018/4/13.
 */
public interface UserRepository extends JpaRepository<UserDT, String> {

    /**
     * 根据账号查找
     * @param username
     * @return
     */
    UserDT findUserDTByUsername(String username);
}
