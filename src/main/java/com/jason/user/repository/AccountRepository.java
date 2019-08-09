package com.jason.user.repository;

import com.jason.user.model.AccountDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by BNC on 2019/7/10.
 */
public interface AccountRepository extends JpaRepository<AccountDO, String> {

    /**
     * 通过用户名查询用户对象
     * @param username 用户名
     * @return 用户对象
     */
    AccountDO findByUsername(String username);
}
