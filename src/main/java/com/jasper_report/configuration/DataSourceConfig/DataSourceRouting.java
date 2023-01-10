//package com.jasper_report.configuration.DataSourceConfig;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Lazy;
//import org.springframework.context.annotation.Scope;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.stereotype.Component;
//
//@Component
//@Lazy
//public class DataSourceRouting extends AbstractRoutingDataSource {
//
//    private DataSourceContextHolder dataSourceContextHolder;
//
//    public void initDatasource(DataSource dataSourceOneConfig,
//                             DataSource dataSourceTwoConfig) {
//
//        Map<Object, Object> dataSourceMap = new HashMap<>();
//        dataSourceMap.put(DataSourceEnum.DATASOURCE_ONE, dataSourceOneConfig);
//        dataSourceMap.put(DataSourceEnum.DATASOURCE_TWO,dataSourceTwoConfig);
//        this.setTargetDataSources(dataSourceMap);
//        this.setDefaultTargetDataSource(dataSourceOneConfig);
//    }
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return dataSourceContextHolder.getBranchContext();
//    }
//
//}