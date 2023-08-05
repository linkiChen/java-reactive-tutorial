package com.jacky.r2dbc.customers;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.MySqlDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import javax.annotation.PostConstruct;

@Configuration
@EnableR2dbcRepositories(databaseClientRef = "customerDbClient")
public class CustomerConfig {


    @Bean
    @Qualifier(value = "customersConnectionFactory")
    public ConnectionFactory customersConnectionFactory() {
        return ConnectionFactories.get("r2dbc:mysql://root:root@localhost:3306/customers");
    }

    @Bean
    public R2dbcEntityOperations customersEntityTemplate(@Qualifier("customersConnectionFactory") ConnectionFactory connectionFactory) {

        DefaultReactiveDataAccessStrategy strategy = new DefaultReactiveDataAccessStrategy(MySqlDialect.INSTANCE);
        DatabaseClient databaseClient = DatabaseClient.builder()
                .connectionFactory(connectionFactory)
//                .bindMarkers(MySqlDialect.INSTANCE.getBindMarkersFactory())
                .dataAccessStrategy(strategy)
                .build();

        return new R2dbcEntityTemplate(databaseClient, strategy);
    }

    @Bean(name = "customerDbClient")
    public DatabaseClient dbClient() {
        return DatabaseClient.create(customersConnectionFactory());
    }

    @PostConstruct
    public void initialize() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScripts(
                new ClassPathResource("scripts/customers/schema.sql"),
                new ClassPathResource("scripts/customers/data.sql")
        );
        databasePopulator.execute(customersConnectionFactory()).subscribe();
//        databasePopulator.populate(customersConnectionFactory()).subscribe();
    }

}
