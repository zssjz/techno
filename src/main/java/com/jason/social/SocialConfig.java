package com.jason.social;

import com.jason.social.qq.connect.QQConnectionFactory;
import com.jason.social.wechat.connect.WeChatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//        return super.getUsersConnectionRepository(connectionFactoryLocator);
        JdbcUsersConnectionRepository repository =
                new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("social_");
        if (connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment env) {
//        super.addConnectionFactories(connectionFactoryConfigurer, environment);
        configurer.addConnectionFactory(new QQConnectionFactory("qq", env.getProperty("social.qq.appId"), env.getProperty("social.qq.appSecret")));
        configurer.addConnectionFactory(new WeChatConnectionFactory("WeChat", env.getProperty("social.wechat.appId"), env.getProperty("social.wechat.secret")));
    }

    @Override
    public UserIdSource getUserIdSource() {
//        return super.getUserIdSource();
        return new AuthenticationNameUserIdSource();
    }

    @Bean
    public SpringSocialConfigurer securitySocialConfigurer(Environment env) {
        SecuritySocialConfigurer configurer =
                new SecuritySocialConfigurer(env.getProperty("social.filterProcessUrl"));
        // 设置注册页
        configurer.signupUrl(env.getProperty("social.signUpUrl"));
        return configurer;
    }

    @Bean
    public ProviderSignInUtils getProviderSignUtils(ConnectionFactoryLocator locator) {
        return new ProviderSignInUtils(locator, getUsersConnectionRepository(locator));
    }
}
