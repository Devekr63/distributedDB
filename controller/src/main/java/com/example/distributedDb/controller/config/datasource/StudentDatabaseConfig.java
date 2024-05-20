package com.example.distributedDb.controller.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "studentEntityManagerFactory",
        transactionManagerRef = "studentTransactionManager",
        basePackages = {"com.example.distributedDb.student_service.repo"}
)

//Primary Data source
public class StudentDatabaseConfig {

    @Primary
    @Bean(name = "studentDataSourceProperties")
    @ConfigurationProperties("spring.db2.datasource")
    public DataSourceProperties studentDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "studentDataSource")
    @ConfigurationProperties("spring.db2.datasource.configuration")
    public DataSource studentDataSource(
            @Qualifier("studentDataSourceProperties")
            DataSourceProperties studentDataSourceProperties
    ){
        return studentDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name ="studentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean studentEntityManagerFactory(
        EntityManagerFactoryBuilder studentEntityManagerFactoryBuilder,
        @Qualifier("studentDataSource") DataSource studentDataSource
    ){
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");

        return studentEntityManagerFactoryBuilder
                .dataSource(studentDataSource)
                .packages("com.example.distributedDb.student_service.dto")
                .persistenceUnit("studentDataSource")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean(name = "studentTransactionManager")
    public PlatformTransactionManager studentTransactionManager(
            @Qualifier("studentEntityManagerFactory") EntityManagerFactory studentEntityManagerFactory
    ){
        return new JpaTransactionManager(studentEntityManagerFactory);
    }
}
