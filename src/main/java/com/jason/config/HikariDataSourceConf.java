package com.jason.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondEntityManagerFactory",
        transactionManagerRef = "secondTransactionManager",
        basePackages = {"com.jason.repository"}
)
public class HikariDataSourceConf implements EnvironmentAware {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"spring.datasource.");
    }

    @Bean(name = "security")
    public DataSource hikariCPDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(propertyResolver.getProperty("security.driver-class-name"));
        dataSource.setJdbcUrl(propertyResolver.getProperty("security.url"));
        dataSource.setUsername(propertyResolver.getProperty("security.username"));
        dataSource.setPassword(propertyResolver.getProperty("security.password"));
        dataSource.setMinimumIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));
        dataSource.setMaximumPoolSize(Integer.valueOf(propertyResolver.getProperty("maxIdle")));
        dataSource.setMaxLifetime(Long.valueOf(propertyResolver.getProperty("maxWait")));
//        dataSource.setTimeBetweenConnectErrorMillis(Long.valueOf(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
//        dataSource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
//        dataSource.setUseGlobalDataSourceStat(Boolean.valueOf(propertyResolver.getProperty("useGlobalDataSourceStat")));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("security") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.jason.entity")
                .persistenceUnit("second")
                .build();
    }

    @Bean
    public PlatformTransactionManager secondTransactionManager(
            @Qualifier("secondEntityManagerFactory") LocalContainerEntityManagerFactoryBean factoryBean) {
        return new JpaTransactionManager(factoryBean.getObject());
    }

}
