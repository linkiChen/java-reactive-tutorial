package com.jacky.r2dbc.orders;

import org.reactivestreams.Subscriber;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class OrderHandler {

    private final OrderRepository orderRepository;

    public OrderHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Flux<Order> allOrders() {
        Flux<Order> all = orderRepository.findAll();
        return all;
    }

    public Mono<Order> saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Mono<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    public Mono<ServerResponse> getAllOrders(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.allOrders(), Order.class);
    }

    public Mono<ServerResponse> createOrder(ServerRequest request) {
        Mono<Order> orderMono = request.bodyToMono(Order.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderRepository.saveAll(orderMono), Order.class);
    }

    public Mono<ServerResponse> deleteOrderById(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        return orderRepository.findById(id)
                .flatMap(order -> this.orderRepository.delete(order)
                        .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }


}
