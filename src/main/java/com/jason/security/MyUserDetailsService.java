package com.jason.security;

import com.jason.components.dao.UserRepository;
import com.jason.entity.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by Jason on 2018/4/16.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = null;
        try {
            userDO = userRepository.findUserDTByUsername(username);
        } catch (Exception e) {
            logger.error("服务器异常：{}", e.getMessage());
            e.printStackTrace();
        }
        if (userDO == null) {
            throw new UsernameNotFoundException("该账号未注册");
        }
        return userDO;
    }
}
