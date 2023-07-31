package com.jacky.r2dbc;

import com.jacky.r2dbc.entities.ApiUserApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MysqlR2dbcTester {

    @Resource
    private DatabaseClient dbClient;


    @Test
    public void clientInsertTest() {
        Mono<Void> insert = dbClient.insert()
                .into("api_user_app")
                .value("app_id", "appId")
                .value("secert", "appSecret")
                .nullValue("status", String.class)
                .then();
        insert.log().subscribe();
    }

    @Test
    public void clientSelectTest() {
        Flux<ApiUserApp> all = dbClient.select()
                .from("api_user_app")
                .matching(Criteria.empty())
                .as(ApiUserApp.class)
                .fetch()
                .all();
        all.onErrorResume(throwable -> {
            System.out.println("throwable:"+throwable);
            return null;
        }).log().subscribe(System.out::println);
    }
}
