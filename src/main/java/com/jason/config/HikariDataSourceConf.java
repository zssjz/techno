package com.jason.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class HikariDataSourceConf implements EnvironmentAware {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"spring.datasource.");
    }

    @Bean(name = "HikariCP")
    @ConfigurationProperties(prefix = "spring.datasource.jason")
    public DataSource hickricp() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        dataSource.setJdbcUrl(propertyResolver.getProperty("url"));
        dataSource.setUsername(propertyResolver.getProperty("username"));
        dataSource.setPassword(propertyResolver.getProperty("password"));
        dataSource.setMinimumIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));
        dataSource.setMaximumPoolSize(Integer.valueOf(propertyResolver.getProperty("maxIdle")));
        dataSource.setMaxLifetime(Long.valueOf(propertyResolver.getProperty("maxWait")));
//        dataSource.setTimeBetweenConnectErrorMillis(Long.valueOf(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
//        dataSource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
//        dataSource.setUseGlobalDataSourceStat(Boolean.valueOf(propertyResolver.getProperty("useGlobalDataSourceStat")));
        return dataSource;
    }
}
