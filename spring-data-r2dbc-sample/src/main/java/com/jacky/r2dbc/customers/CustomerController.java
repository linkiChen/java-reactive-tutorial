package com.jacky.r2dbc.customers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customers;

    @GetMapping("")
    public ResponseEntity<Flux<Customer>> all() {
        return ResponseEntity.ok().body(this.customers.findAll());
    }

}
