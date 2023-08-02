package com.jacky.r2dbc;

import com.jacky.r2dbc.entities.ApiUserApp;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MysqlR2dbcTester {

    @Resource
    private ConnectionFactory connectionFactory;

    private DatabaseClient dbClient;

    @Before
    public void before() {
        dbClient = DatabaseClient.create(connectionFactory);
    }


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
    public void clientUpdateTest() {
        dbClient.update().table("api_user_app")
                .using(Update.update("status", "EFFECT")
                        .set("remark", "测试databaseClient更新"))
                .matching(Criteria.where("app_id").is("appId"))
                .fetch()
                .rowsUpdated()
                .subscribe(rows -> System.out.println("更新记录数:" + rows));
    }

    @Test
    public void clientSelectTest() {
        Flux<ApiUserApp> all = dbClient.select()
                .from("api_user_app")
                .matching(Criteria.empty())
                .as(ApiUserApp.class)
                .fetch()
                .all();
        all.subscribe(System.out::println);
    }

    @Test
    public void clientDeleteTest() {
        dbClient.delete()
                .from(ApiUserApp.class)
                .matching(Criteria.where("app_id").is("appId")
                        .and("secert").is("appSecret"))
                .fetch() // 执行sql
                .rowsUpdated() // 获取更新的记录数
                .subscribe(rows -> System.out.println("删除的记录数为:" + rows));
    }

}
