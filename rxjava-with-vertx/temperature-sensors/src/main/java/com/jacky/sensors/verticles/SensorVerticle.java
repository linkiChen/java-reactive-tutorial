package com.jacky.sensors.verticles;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

public class SensorVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorVerticle.class);
    private static final int httpPort = Integer.parseInt(System.getenv().getOrDefault("HTTP_PORT", "8080"));

    private final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    private double temperature = 21.0;
    private final Random random = new Random();

    @Override
    public void start() throws Exception {
        vertx.setPeriodic(2000, this::updateTemperature);
    }

    private void updateTemperature(Long aLong) {
        temperature = temperature + (random.nextGaussian() / 2.0d);
//        System.out.println("Temperature updated: "+temperature);
        LOGGER.info("Temperature updated: {}", temperature);
    }
}
