package com.jacky.r2dbc.controller;

import com.jacky.r2dbc.orders.OrderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


/**
 * 函数式编程接口
 */
@Configuration
public class OrderRouteFunction {

    @Bean
    RouterFunction<ServerResponse> orderRoue(OrderHandler handler) {
        return RouterFunctions.nest(
                RequestPredicates.path("/order"),
                RouterFunctions.route(
                        RequestPredicates.GET("/all"), handler::getAllOrders)
                        .andRoute(RequestPredicates.POST("/save").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::createOrder)
                        .andRoute(RequestPredicates.DELETE("/{id}"), handler::deleteOrderById));
    }

}
