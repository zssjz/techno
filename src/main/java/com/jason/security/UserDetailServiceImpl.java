package com.jason.security;

import com.jason.user.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by BNC on 2019/8/6.
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private AccountRepository repository;

    /**
     * 表单登录
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByUsername(s);
    }

    /**
     * 社交登录
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        return new SocialUser(s, "", AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
    }
}
