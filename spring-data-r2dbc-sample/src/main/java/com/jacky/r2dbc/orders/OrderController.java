package com.jacky.r2dbc.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orders;

    @GetMapping("")
    public ResponseEntity<Flux<Order>> all() {
        return ResponseEntity.ok().body(this.orders.findAll());
    }

}
