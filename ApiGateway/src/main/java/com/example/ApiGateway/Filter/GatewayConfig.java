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
//                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:4004"))
                .route("AuthService", r -> r.path("/api/v1/auth/**")
//                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:4003"))
                .route("TravelAppService", r -> r.path("/api/v1/request-service/**")
//                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:4005"))
                .route("CartService", r -> r.path("/api/v1/cart/**")
//                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:4006"))
                .route("OAuth", r -> r.path("/api/v1/oauth/**")
//                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:4008"))
                .route("EmailService", r -> r.path("/api/v1/email-service/**")
//                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:4009"))
                .build();
    }
}