package com.zero.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eureka-服务注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class ZeroEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeroEurekaServerApplication.class, args);
    }
}

