package com.jacky.sensors;

import com.jacky.sensors.verticles.SensorVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
//        vertx.deployVerticle(new SensorVerticle());
        // 部署三个SensorVerticle实例 todo 不知道为这什么这样没成功
        vertx.deployVerticle("SensorVerticle",new DeploymentOptions().setInstances(3));
    }
}
