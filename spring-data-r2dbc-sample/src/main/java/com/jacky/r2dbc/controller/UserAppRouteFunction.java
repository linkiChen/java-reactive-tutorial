package com.jacky.r2dbc.controller;

import com.jacky.r2dbc.repositories.ReactiveUserAppRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import javax.annotation.Resource;

/**
 * 函数式编程接口
 */
@Configuration
public class UserAppRouteFunction {

    @Resource
    private ReactiveUserAppRepository userAppRepository;

}
