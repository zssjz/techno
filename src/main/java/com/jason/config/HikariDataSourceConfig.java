package com.jason.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
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

/**
 * Created by BNC on 2019/7/11.
 */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "technoEntityManagerFactory",
//        transactionManagerRef = "technoTransactionManager",
//        basePackages = {
//        }
//)
public class HikariDataSourceConfig {

    @Primary
    @Bean(name = {"hikariDataSource", "techno"})
    public DataSource hikariDataSource(Environment env) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.techno.datasource.driver-class-name"));
        dataSource.setJdbcUrl(env.getProperty("spring.techno.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.techno.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.techno.datasource.password"));
        dataSource.setMinimumIdle(Integer.valueOf(env.getProperty("spring.techno.datasource.minimum-idle")));
        dataSource.setMaximumPoolSize(Integer.valueOf(env.getProperty("spring.techno.datasource.maximum-pool-size")));
        dataSource.setConnectionTimeout(Long.valueOf(env.getProperty("spring.techno.datasource.connection-timeout")));
        dataSource.setMaxLifetime(Long.valueOf(env.getProperty("spring.techno.datasource.max-lifetime")));
        dataSource.setIdleTimeout(Long.valueOf(env.getProperty("spring.techno.datasource.idle-timeout")));
        return dataSource;
    }


    @Bean(name = "technoEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean technoEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("techno") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.jason.user.model")
                .persistenceUnit("components")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager technoTransactionManager(@Qualifier("technoEntityManagerFactory") LocalContainerEntityManagerFactoryBean factoryBean) {
        return new JpaTransactionManager(factoryBean.getObject());
    }
}
