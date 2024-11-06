package org.ecommerce.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @OpenAPIDefinition(
            info = @Info(
                    title = "Order API",
                    version = "v1",
                    description = "E-commerce Order Service"
            )
    )
    @Configuration
    public class SwaggerConfig {
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info().title("Order API").version("v1"));
        }
    }

