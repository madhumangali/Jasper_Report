package com.jasper_report.configuration;


import com.zaxxer.hikari.HikariDataSource;

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

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.jasper_report.repository.primary.*",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef= "batchTransactionManager"
)
public class PrimaryConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.asses")
    public DataSourceProperties batchDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.asses.configuration")
    public DataSource batchDataSource() {
        return batchDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @PersistenceContext(unitName = "primary")
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean batchEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(batchDataSource())
                .packages("com.jasper_report.model.domain")
                .persistenceUnit("primary")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager batchTransactionManager(
            final @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean
                    batchEntityManagerFactory) {
        return new JpaTransactionManager(batchEntityManagerFactory.getObject());
    }



}
