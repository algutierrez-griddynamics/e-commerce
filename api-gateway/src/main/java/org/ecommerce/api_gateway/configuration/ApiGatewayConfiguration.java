package org.ecommerce.api_gateway.configuration;

import org.ecommerce.api_gateway.InMemoryRoles;
import org.ecommerce.api_gateway.RoleBasedFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator apiGatewayRouteLocator(RouteLocatorBuilder builder, RoleBasedFilter roleBasedFilter) {
        return builder.routes()
                .route(p -> p.path("/orders/**")
                        .filters(f -> f.filter(roleBasedFilter.apply(new InMemoryRoles(List.of("MANAGER")))))
                        .uri("lb://orders"))
                .route(p -> p.path("/billing-information/**")
                        .filters(f -> f.filter(roleBasedFilter.apply(new InMemoryRoles(List.of("CASHIER", "MANAGER")))))
                        .uri("lb://billing-information-service"))
                .route(p -> p.path("/shipping-information/**")
                        .filters(f -> f.filter(roleBasedFilter.apply(new InMemoryRoles(List.of("MANAGER")))))
                        .uri("lb://shipping-information-service"))
                .route(p -> p.path("/payment-details/**")
                        .filters(f -> f.filter(roleBasedFilter.apply(new InMemoryRoles(List.of("CASHIER")))))
                        .uri("lb://payment-details-service"))
                .build();
    }
}
