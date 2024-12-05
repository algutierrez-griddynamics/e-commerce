package org.ecommerce.api_gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator apiGatewayRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/orders/**")
                        .uri("lb://orders"))
                .route(p -> p.path("/billing-information/**")
                        .uri("lb://billing-information-service"))
                .route(p -> p.path("/shipping-information/**")
                        .uri("lb://shipping-information-service"))
                .route(p -> p.path("/payment-details/**")
                        .uri("lb://payment-details-service"))
                .build();

    }

}