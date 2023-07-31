package com.jacky.r2dbc.controller;

import com.jacky.r2dbc.entities.ApiUserApp;
import com.jacky.r2dbc.repositories.ReactiveUserAppRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping("userApp")
public class ReactiveUserAppController {

    @Resource
    private ReactiveUserAppRepository userAppRepository;

    @GetMapping("/{id}")
    public Mono<ApiUserApp> findUserByAppId(@PathVariable Long id) {
        return userAppRepository.findById(id);
    }

    @GetMapping("/byAppId/{appId}")
    public Mono<ApiUserApp> findUserByAppId(@PathVariable String appId) {
        ApiUserApp defaultRet = new ApiUserApp();
        return userAppRepository.findByAppId(appId).log().defaultIfEmpty(defaultRet);
    }

    @GetMapping("/findAll")
    public Flux<ApiUserApp> findAllUserApp() {
        return userAppRepository.findAll().log();
    }
}
