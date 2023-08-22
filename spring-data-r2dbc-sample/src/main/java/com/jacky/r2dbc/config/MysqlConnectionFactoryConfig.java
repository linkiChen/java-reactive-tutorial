package com.jacky.r2dbc.config;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.MySqlDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.ReactiveTransaction;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

import java.time.Duration;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.jacky.r2dbc.repositories", entityOperationsRef = "globalR2dbcEntityOperations")
public class MysqlConnectionFactoryConfig {
    /**
     * reactive java config方式连接mysql数据库配置
     *
     * @return
     */
    @Bean
    public ConnectionFactory mysqlConnectionFactory() {
        MySqlConnectionFactory connectionFactory = MySqlConnectionFactory.from(
                MySqlConnectionConfiguration
                        .builder()
                        .host("10.167.150.29")
                        .port(3306)
                        .username("data_usr")
                        .password("GHYwlh123adzx")
                        .database("open_api")
                        .build()
        );
        ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration.builder(connectionFactory)
                .maxIdleTime(Duration.ofMillis(2000))
                .maxSize(10)
                .initialSize(3)
                .validationQuery("select 1")
                .name("r2dbc-connection-pool")
                .build();
//        return connectionFactory;
        return new ConnectionPool(poolConfiguration);
    }

    @Bean
    public R2dbcEntityTemplate globalR2dbcEntityOperations(@Qualifier("mysqlConnectionFactory") ConnectionFactory connectionFactory) {

        DefaultReactiveDataAccessStrategy strategy = new DefaultReactiveDataAccessStrategy(MySqlDialect.INSTANCE);
        DatabaseClient databaseClient = DatabaseClient.builder()
                .connectionFactory(connectionFactory)
//                .bindMarkers(MySqlDialect.INSTANCE.getBindMarkersFactory())
                .dataAccessStrategy(strategy)
                .build();

        return new R2dbcEntityTemplate(databaseClient, strategy);
    }

    @Bean
    public ReactiveTransactionManager transactionManager(@Qualifier(value = "mysqlConnectionFactory") ConnectionFactory connectionFactory) {
        R2dbcTransactionManager txManager = new R2dbcTransactionManager(connectionFactory);
        return txManager;
    }

    @Bean
    public TransactionalOperator transactionalOperator(ReactiveTransactionManager txManager) {
        return TransactionalOperator.create(txManager);
    }

    @Bean
    public DatabaseClient dbClient() {
        return DatabaseClient.create(mysqlConnectionFactory());
    }
}
