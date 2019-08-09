package com.jason.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BNC on 2019/7/11.
 */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "securityEntityManagerFactory",
//        transactionManagerRef = "securityTransactionManager",
//        basePackages = {"com.jason.user.repository"}
//)
public class DruidDataSourceConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JpaProperties jpaProperties;

    @Bean(name = {"druidDataSource", "security"})
    public DataSource druidDataSource(Environment env) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.security.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.security.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.security.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.security.datasource.password"));
        dataSource.setInitialSize(Integer.valueOf(env.getProperty("spring.security.datasource.initialSize")));
        dataSource.setMinIdle(Integer.valueOf(env.getProperty("spring.security.datasource.minIdle")));
        dataSource.setTimeBetweenConnectErrorMillis(Long.valueOf(env.getProperty("spring.security.datasource.timeBetweenEvictionRunsMillis")));
        dataSource.setValidationQueryTimeout(Integer.valueOf(env.getProperty("spring.security.datasource.validationQueryTimeout")));
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean druidServlet(Environment env) {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> property = new HashMap<>();
//        property.put("loginUsername", env.getProperty("spring.datasource.security.loginUsername"));
//        property.put("loginPassword", env.getProperty("spring.datasource.security.loginPassword"));
//        property.put("allow", env.getProperty("spring.datasource.security.allow"));
//        property.put("deny", propertyResolver.getProperty("deny"));
        property.put("restEnable", env.getProperty("spring.security.datasource.restEnable"));
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new WebStatFilter());
        filter.addUrlPatterns("/*");
        filter.addInitParameter("exclusions", "*.js,*.css,*.gif,*.png,*.jpg,*.ico,/druid/*");
        return filter;
    }

    @Bean(name = "securityEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean securityEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("security") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.jason.user.model")
                .persistenceUnit("security")
                .build();
    }

    @Bean
    public PlatformTransactionManager securityTransactionManager(
            @Qualifier("securityEntityManagerFactory") LocalContainerEntityManagerFactoryBean factoryBean) {
        return new JpaTransactionManager(factoryBean.getObject());
    }
}
