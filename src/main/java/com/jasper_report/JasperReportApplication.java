package com.jasper_report;



import com.jasper_report.mapper.*;
import com.jasper_report.repository.primary.PrimaryCommonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.jasper_report.*","com.jasper_report.repository.primary.PrimaryCommonRepository"})
@EnableJpaRepositories(basePackages = {"com.jasper_report.repository.secondary.SecondaryCommonRepository"},
        basePackageClasses = {PrimaryCommonRepository.class})
public class JasperReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(JasperReportApplication.class, args);
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
