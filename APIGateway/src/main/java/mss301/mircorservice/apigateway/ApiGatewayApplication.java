package mss301.mircorservice.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("account-service", r -> r.path("/api/auth/**")
                        .uri("http://localhost:8081"))
                .route("brand-service", r -> r.path("/api/brands/**")
                        .uri("http://localhost:8082"))
                .route("blindbox-service", r -> r.path("/api/blind-boxes/**")
                        .uri("http://localhost:8083"))
                .build();
    }
}
