package com.jacky.r2dbc.config;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

@Configuration

public class MysqlConnectionFactoryConfig {
    /**
     * reactive java config方式连接mysql数据库配置
     *
     * @return
     */
    @Bean
    public ConnectionFactory mysqlConnectionFactory() {
        return MySqlConnectionFactory.from(
                MySqlConnectionConfiguration
                        .builder()
                        .host("10.167.150.29")
                        .port(3306)
                        .username("data_usr")
                        .password("GHYwlh123adzx")
                        .database("open_api")
                        .build()
        );
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }
}
