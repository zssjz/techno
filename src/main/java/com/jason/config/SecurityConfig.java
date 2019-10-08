package com.jason.config;

import com.jason.security.ExpiredSessionStrategy;
import com.jason.security.filter.ValidateImageCodeFilter;
import com.jason.security.handler.IdentificationFailureHandler;
import com.jason.security.handler.IdentificationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by BNC on 2019/7/11.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IdentificationSuccessHandler identificationSuccessHandler;

    @Autowired
    private IdentificationFailureHandler identificationFailureHandler;

    @Autowired
    private ValidateImageCodeFilter validateImageCodeFilter;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Resource
    private DataSource dataSource;

    @Resource(name = "userDetailServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Value("${social.signUpUrl}")
    private String signUpUrl;

    @Bean
    public PersistentTokenRepository persistentTokenRepository () {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
        web.ignoring().antMatchers("/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.httpBasic()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated();

        http.addFilterBefore(validateImageCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .apply(springSocialConfigurer)
                .and()
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/form/login")              // 自定义的登录接口
                .successHandler(identificationSuccessHandler)   // 自定义认证成功处理器
                .failureHandler(identificationFailureHandler)   // 自定义认证失败处理器
                .and()
                .rememberMe()                                   // 记住我
                .tokenRepository(persistentTokenRepository())   // 数据库记录
                .tokenValiditySeconds(3600 * 24 * 3)            // 过期时间
                .userDetailsService(userDetailsService)         // 登录方法
                .and()
                .sessionManagement()
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true) // 当session达到最大数量时，限制后续session登录请求
                .expiredSessionStrategy(new ExpiredSessionStrategy())
                .and()
                .and()
                .authorizeRequests()
                .antMatchers(                                   // 不经过认证的url
                        "/authentication/require",  // 跳转处理
            "/login",                                           // 重定向
            "/login.html",                                      // 登录页
            "/code/*",                                           // 验证码
                        signUpUrl,
                "/session/invalid"
                ).permitAll()
                .anyRequest()
                .authenticated();
    }
}
