package com.example.ApiGateway.Filter;

import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteValidator {

    public static final List<String> openEndpoints = List.of(
            "/api/v1/auth/**",
            "/api/v1/request-service/**",
            "/api/v1/oauth/**",
            "/api/v1/email-service/**"
    );

    public Predicate<ServerHttpRequest> isSecured =
            ( request -> openEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri)));
}