//package com.jasper_report.configuration.DataSourceConfig;
//
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
//@Lazy
//public class DataSourceContextHolder {
//    private static ThreadLocal<DataSourceEnum> threadLocal = new ThreadLocal<>();
//
//
////    public DataSourceContextHolder() {
////        threadLocal = new ThreadLocal<>();
////    }
//
//    public static void setBranchContext(DataSourceEnum dataSourceEnum) {
//        threadLocal.set(dataSourceEnum);
//    }
//
//    public static DataSourceEnum getBranchContext() {
//        return threadLocal.get();
//    }
//
//    public static void clearBranchContext() {
//        threadLocal.remove();
//    }
//}