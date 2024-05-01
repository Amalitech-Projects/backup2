package com.example.ApiGateway.Filter;

import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteValidator {

    public static final List<String> openEndpoints = List.of(
            "/api/v1/auth/register",
            "/api/v1/auth/login"
    );

    public Predicate<ServerHttpRequest> isSecured =
            ( request -> openEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri)));
}