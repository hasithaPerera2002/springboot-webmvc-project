package cw.hasitha.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;


/********************************************************************
 * --
 * Author: hasitha
 * Date: 10/11/2023
 * --
 ********************************************************************/

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/api/v1/user/register",
            "/api/v1/user/login"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
