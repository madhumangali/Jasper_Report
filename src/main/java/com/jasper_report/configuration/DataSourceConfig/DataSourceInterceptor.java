//package com.jasper_report.configuration.DataSourceConfig;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//
//@Component
//public class DataSourceInterceptor extends HandlerInterceptorAdapter {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object handler) throws Exception {
//
//        String branch = request.getHeader("branch");
//        if (DataSourceEnum.DATASOURCE_ONE.toString().equalsIgnoreCase(branch))
//            DataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_ONE);
//        else
//            DataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_TWO);
//        return super.preHandle(request, response, handler);
//    }
//}