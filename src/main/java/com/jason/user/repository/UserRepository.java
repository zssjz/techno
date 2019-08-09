package com.jason.user.repository;

import com.jason.user.model.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by BNC on 2019/7/2.
 */
public interface UserRepository extends JpaRepository<UserDO, String> {
}
