package com.jasper_report.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;

class OpenApiConfigurationTest {
    /**
     * Method under test: {@link OpenApiConfiguration#customOpenApi()}
     */
    @Test
    void testCustomOpenApi() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OpenAPI actualCustomOpenApiResult = (new OpenApiConfiguration()).customOpenApi();
        assertNull(actualCustomOpenApiResult.getTags());
        assertNull(actualCustomOpenApiResult.getExtensions());
        assertNull(actualCustomOpenApiResult.getServers());
        assertEquals("3.0.1", actualCustomOpenApiResult.getOpenapi());
        assertNull(actualCustomOpenApiResult.getExternalDocs());
        assertNull(actualCustomOpenApiResult.getPaths());
        assertNull(actualCustomOpenApiResult.getSecurity());
        Info info = actualCustomOpenApiResult.getInfo();
        assertNull(info.getExtensions());
        assertEquals("A sample Report Generation", info.getDescription());
        assertNull(info.getContact());
        Components components = actualCustomOpenApiResult.getComponents();
        assertNull(components.getRequestBodies());
        assertNull(components.getParameters());
        assertNull(components.getLinks());
        assertNull(components.getHeaders());
        assertNull(components.getExtensions());
        assertNull(components.getExamples());
        assertNull(components.getCallbacks());
        assertNull(components.getSecuritySchemes());
        assertNull(info.getTermsOfService());
        assertEquals("Jasper_Report_Service", info.getTitle());
        assertNull(components.getResponses());
        assertNull(components.getSchemas());
        assertNull(info.getLicense());
        assertNull(info.getVersion());
    }
}

