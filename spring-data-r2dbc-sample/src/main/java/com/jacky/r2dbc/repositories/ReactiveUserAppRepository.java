package com.jacky.r2dbc.repositories;

import com.jacky.r2dbc.entities.ApiUserApp;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

//public interface ReactiveUserAppRepository extends ReactiveSortingRepository<ApiUserApp, Long> {
public interface ReactiveUserAppRepository extends R2dbcRepository<ApiUserApp, Long> {

    @Query("select * from api_user_app where app_id = :appId")
    Mono<ApiUserApp> findByAppId(String appId);
}
