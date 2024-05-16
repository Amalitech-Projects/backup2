package com.example.ApiGateway.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableHystrix
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        System.out.println("Anything");
        return builder.routes()
                .route("UserService", r -> r.path("/api/v1/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lg://UserService"))
                .route("AuthService", r -> r.path("/api/v1/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://AuthService"))
                .route("TravelAppService", r -> r.path("/api/v1/request-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://TravelAppService"))
                .route("CartService", r -> r.path("/api/v1/cart/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://CartService"))
                .route("OAuth", r -> r.path("/api/v1/oauth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://0Auth"))
                .build();
    }
}