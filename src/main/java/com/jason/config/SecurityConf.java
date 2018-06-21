package com.jason.config;

import com.jason.security.MyAuthenticationFailureHandler;
import com.jason.security.MyAuthenticationSuccessHandler;
import com.jason.security.MyLogoutHandler;
import com.jason.security.MyLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security 配置类
 * Created by Jason on 2018/4/13.
 */
@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Autowired
    private MyLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private MyLogoutHandler logoutHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
        // 静态资源放权
        web.ignoring()
                .antMatchers("/css/**","/js/**","/img/**","**/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);

        // 所有请求通过权限验证，用于测试
//        http.authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .and()
//                .csrf().disable();

        http.formLogin()    // 基于表单的认证
                .loginPage("/login")    // 自定义登录页面
                .successHandler(successHandler) // 登录成功处理
                .failureHandler(failureHandler) // 登录失败处理
                .loginProcessingUrl("/checkIn");   // 登录请求

        http.authorizeRequests()    // 任何请求
                .antMatchers("/","/index","/login","/logout","/resources/**").permitAll()    // 允许所有用户访问
                .antMatchers("/druid/**","/swagger/**","/manage/**").hasRole("ADMIN")  // ADMIN权限才能访问（hasRole方法不需要前缀“ROLE_”）
                .anyRequest()   // 其他请求
                .authenticated()    // 需要权限验证
                .and()
                .csrf().disable();  // 关闭CSRF 验证

        http.logout()   // 登出
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler) // 登出成功处理
                .invalidateHttpSession(true)    // 注销时HTTPSession无效
                .addLogoutHandler(logoutHandler);    // 登出处理

//        http.sessionManagement()
//                .maximumSessions(1);  // 同一账号仅允许在线数量
    }

}
