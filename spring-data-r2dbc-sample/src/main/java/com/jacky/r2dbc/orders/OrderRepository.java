package com.jacky.r2dbc.orders;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface OrderRepository extends R2dbcRepository<Order,Integer> {
}