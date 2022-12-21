package com.jasper_report.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is written for configuring the swagger V3
 */
@Configuration
@Log4j2
public class OpenApiConfiguration {

    /**
     * This Method is written for the purpose to set title and description for the Swagger Document
     */
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
