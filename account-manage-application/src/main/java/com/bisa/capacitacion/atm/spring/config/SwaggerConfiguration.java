package com.bisa.capacitacion.atm.spring.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Miguel A. Vega
 * @version 1.0: SwaggerConfiguration.java;
 */
@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi productosAPI() {
        //read more about: https://github.com/springdoc/springdoc-openapi/issues/213
        return GroupedOpenApi.builder().
                group("Productos API").
                pathsToMatch("/productos/**").
                build();
    }

    @Bean
    public GroupedOpenApi pedidosAPI() {
        //read more about: https://github.com/springdoc/springdoc-openapi/issues/213
        return GroupedOpenApi.builder().
                group("Pedidos API").
                pathsToMatch("/pedidos/**").
                build();
    }

    @Bean
    public GroupedOpenApi accountManagerAPI() {
        //read more about: https://github.com/springdoc/springdoc-openapi/issues/213
        return GroupedOpenApi.builder().
                group("Account Manager API").
                pathsToMatch("/api/account/**").
                build();
    }
}
