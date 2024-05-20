package com.example.distributedDb.controller.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "studentInfoEntityManagerFactory",
        transactionManagerRef = "studentInfoTransactionManager",
        basePackages = {"com.example.distributedDb.department_service.repo"}
)

//Secondary data source
public class StudentInfoDatabaseConfig {

    @Bean(name = "studentInfoDataSourceProperties")
    @ConfigurationProperties("spring.db1.datasource")
    public DataSourceProperties studentInfoDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean(name ="studentInfoDataSource")
    @ConfigurationProperties("spring.db1.datasource.configuration")
    public DataSource studentInfoDataSource(
            @Qualifier("studentInfoDataSourceProperties")
            DataSourceProperties studentInfoDataSourceProperties
    ){
        return studentInfoDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "studentInfoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean studentInfoEntityManagerFactory(
            EntityManagerFactoryBuilder studentInfoEntityManagerFactoryBuilder,
            @Qualifier("studentInfoDataSource") DataSource studentInfoDataSource
    ){
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");

        return studentInfoEntityManagerFactoryBuilder
                .dataSource(studentInfoDataSource)
                .packages("com.example.distributedDb.department_service.dto")
                .persistenceUnit("studentInfoDataSource")
                .properties(properties)
                .build();
    }

    @Bean(name = "studentInfoTransactionManager")
    public PlatformTransactionManager studentInfoTransactionManager(
            @Qualifier("studentInfoEntityManagerFactory") EntityManagerFactory studentInfoEntityManagerFactory
    ){
        return new JpaTransactionManager(studentInfoEntityManagerFactory);
    }
}
