package com.jacky.r2dbc.orders;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.r2dbc.core.*;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import javax.annotation.PostConstruct;


//https://github.com/hantsy/spring-puzzles

@Configuration
@EnableR2dbcRepositories(databaseClientRef = "orderDbClient")
public class OrderConfig {

    @Bean()
    @Qualifier("ordersConnectionFactory")
    public ConnectionFactory ordersConnectionFactory() {
        return ConnectionFactories.get("r2dbc:postgres://postgres:postgres@localhost:5432/orders");
    }


    @Bean
    public R2dbcEntityOperations ordersEntityTemplate(@Qualifier("ordersConnectionFactory") ConnectionFactory connectionFactory) {

        DefaultReactiveDataAccessStrategy strategy = new DefaultReactiveDataAccessStrategy(PostgresDialect.INSTANCE);
        DatabaseClient databaseClient = DatabaseClient.builder()
                .connectionFactory(connectionFactory)
//                .bindMarkers(PostgresDialect.INSTANCE.getBindMarkersFactory())
                .dataAccessStrategy(strategy)
                .build();

        return new R2dbcEntityTemplate(databaseClient, strategy);
    }


    @Bean(name = "orderDbClient")
    public DatabaseClient dbClient() {
        return DatabaseClient.create(ordersConnectionFactory());
    }


    @PostConstruct
    public void initialize() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScripts(
                new ClassPathResource("scripts/orders/schema.sql"),
                new ClassPathResource("scripts/orders/data.sql")
        );
        databasePopulator.execute(ordersConnectionFactory()).subscribe();
//        databasePopulator.populate(ordersConnectionFactory()).subscribe();
    }
}
