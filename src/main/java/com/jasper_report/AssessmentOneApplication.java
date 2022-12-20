package com.jasper_report;


import com.jasper_report.mapper.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com.assessmentone.*")
public class AssessmentOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssessmentOneApplication.class, args);
        System.setProperty("java.awt.headless", "false");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public StyleBuilderMapper getStyleBuilderMapper() {
        return new StyleBuilderMapperImpl();
    }

    @Bean
    public EntityMapper getEntityMapper() {
        return new EntityMapperImpl();
    }
    @Bean
    public EntityPropertyMapper getEntityPropertyMapper() {
        return new EntityPropertyMapperImpl();
    }

    @Bean
    public MultiTableColumnsResultMapper getMultiTableColumnsResultMapper() {
        return new MultiTableColumnsResultMapperImpl();
    }

}
