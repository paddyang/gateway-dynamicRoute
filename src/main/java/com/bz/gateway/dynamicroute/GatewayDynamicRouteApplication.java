package com.bz.gateway.dynamicroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class GatewayDynamicRouteApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayDynamicRouteApplication.class, args);
    }

}
