package com.jasper_report.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi(){

        log.info("Swagger Configuration was done");

        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Jasper_Report_Service")
                        .description("A sample Report Generation")
                        );

    }

}
