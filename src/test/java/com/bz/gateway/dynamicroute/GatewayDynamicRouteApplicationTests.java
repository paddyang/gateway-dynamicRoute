package com.bz.gateway.dynamicroute;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

@SpringBootTest
class GatewayDynamicRouteApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void tt() {
        PredicateDefinition predicateDefinition = JSON.parseObject("", PredicateDefinition.class);

    }


}
