package com.jason.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

@Configuration
public class HikariDataSourceConf implements EnvironmentAware {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"spring.datasource.");
    }

    @Bean(name = "HikariCP")
    public DataSource hickricp() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(propertyResolver.getProperty("jason.driver-class-name"));
        dataSource.setJdbcUrl(propertyResolver.getProperty("jason.url"));
        dataSource.setUsername(propertyResolver.getProperty("jason.username"));
        dataSource.setPassword(propertyResolver.getProperty("jason.password"));
        dataSource.setMinimumIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));
        dataSource.setMaximumPoolSize(Integer.valueOf(propertyResolver.getProperty("maxIdle")));
        dataSource.setMaxLifetime(Long.valueOf(propertyResolver.getProperty("maxWait")));
//        dataSource.setTimeBetweenConnectErrorMillis(Long.valueOf(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
//        dataSource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
//        dataSource.setUseGlobalDataSourceStat(Boolean.valueOf(propertyResolver.getProperty("useGlobalDataSourceStat")));
        return dataSource;
    }

//    @Bean
//    public DataSourceTransactionManager transactionManager(@Qualifier("HikariCP") DataSource dataSource) {
//        return null;
//    }
}
