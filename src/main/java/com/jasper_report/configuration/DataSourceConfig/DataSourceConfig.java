//package com.jasper_report.configuration.DataSourceConfig;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.*;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//
//@Configuration
//@EnableJpaRepositories(basePackages = "com.jasper_report.repository.primary",
//        transactionManagerRef = "transactionManager",
//        entityManagerFactoryRef = "entityManager")
//@EnableTransactionManagement
//public class DataSourceConfig {
//
//    @Autowired
//    private DataSourceOneConfig dataSourceOneConfig;
//    @Autowired
//    private DataSourceTwoConfig dataSourceTwoConfig;
//
//    @Bean
//    @Primary
//    @Autowired
//    public DataSource dataSource() {
//        DataSourceRouting routingDataSource = new DataSourceRouting();
//        routingDataSource.initDatasource(dataSourceOne(),
//                dataSourceTwo());
//        return routingDataSource;
//    }
//
//    public DataSource dataSourceOne() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(dataSourceOneConfig.getUrl());
//        dataSource.setUsername(dataSourceOneConfig.getUsername());
//        dataSource.setPassword(dataSourceOneConfig.getPassword());
//        return dataSource;
//    }
//
//    public DataSource dataSourceTwo() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(dataSourceTwoConfig.getUrl());
//        dataSource.setUsername(dataSourceTwoConfig.getUsername());
//        dataSource.setPassword(dataSourceTwoConfig.getPassword());
//        return dataSource;
//    }
//
//    @Bean(name = "entityManager")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
//            EntityManagerFactoryBuilder builder) {
//        return builder.dataSource(dataSource()).packages("com.jasper_report.model.domain")
//                .build();
//    }
//
////    @Bean(name = "entityManager")
////    public LocalContainerEntityManagerFactoryBean entityManagerBean(
////            EntityManagerFactoryBuilder builder) {
////        return builder.dataSource(dataSource()).packages("com.jasper_report.model.properties")
////                .build();
////    }
//
//    @Bean(name = "transactionManager")
//    @Primary
//    public JpaTransactionManager transactionManager(
//            @Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
//        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
//    }
//
//}