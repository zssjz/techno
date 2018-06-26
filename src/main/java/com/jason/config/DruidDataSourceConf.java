package com.jason.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 主数据源
 * Created by Jason on 2018/4/13.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = {"com.jason.components.dao"}
)
public class DruidDataSourceConf implements EnvironmentAware {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RelaxedPropertyResolver propertyResolver;

   @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"spring.datasource.");
    }

    @Bean(name = "techno")
    @Primary
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(propertyResolver.getProperty("techno.driver-class-name"));
        dataSource.setUrl(propertyResolver.getProperty("techno.url"));
        dataSource.setUsername(propertyResolver.getProperty("techno.username"));
        dataSource.setPassword(propertyResolver.getProperty("techno.password"));
        dataSource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));
        dataSource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("maxIdle")));
        dataSource.setMaxWait(Long.valueOf(propertyResolver.getProperty("maxWait")));
        dataSource.setTimeBetweenConnectErrorMillis(Long.valueOf(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
        dataSource.setUseGlobalDataSourceStat(Boolean.valueOf(propertyResolver.getProperty("useGlobalDataSourceStat")));
        try {
            dataSource.setFilters(propertyResolver.getProperty("filters"));
        } catch (SQLException e) {
            logger.error("Create DruidDataSource failed:{}", e.getMessage());
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParam = new HashMap<String, String>();
//        initParam.put("loginUsername", propertyResolver.getProperty("loginUsername"));
//        initParam.put("loginPassword", propertyResolver.getProperty("loginPassword"));
        initParam.put("allow",propertyResolver.getProperty("allow"));
//        initParam.put("deny", propertyResolver.getProperty("deny"));
        initParam.put("restEnable", propertyResolver.getProperty("restEnable"));
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.css,*.gif,*.png,*.jpg,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    /**
     * entityManagerFactory
     * @param builder
     * @param dataSource
     * @return
     */
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("techno") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.jason.components.model")
                .persistenceUnit("primary")
                .build();
    }

    /**
     * 事务管理
     * @param factoryBean
     * @return
     */
    @Bean
    @Primary
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean factoryBean) {
        return new JpaTransactionManager(factoryBean.getObject());
    }

}
