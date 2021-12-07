package com.bz.gateway.dynamicroute.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bz.gateway.dynamicroute.entity.GatewayRoute;
import com.bz.gateway.dynamicroute.mapper.GatewayRouteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class GatewayServiceHandler implements ApplicationEventPublisherAware, CommandLineRunner {

    private final RedisRouteDefinitionRepository routeDefinitionWriter;

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    //自己的获取数据dao
    private final GatewayRouteMapper gatewayRouteMapper;
    private final StringRedisTemplate redisTemplate;


    @Override

    public void run(String... args){
        this.loadRouteConfig();
    }

    public String loadRouteConfig() {
        log.info("====开始加载=====网关配置信息=========");
        //删除redis里面的路由配置信息
        redisTemplate.delete(RedisRouteDefinitionRepository.GATEWAY_ROUTES);

        //从数据库拿到基本路由配置
        QueryWrapper<GatewayRoute> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag","0");
        List<GatewayRoute> gatewayRouteList = gatewayRouteMapper.selectList(wrapper);
        gatewayRouteList.forEach(gatewayRoute -> {
            RouteDefinition definition=handleData(gatewayRoute);
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        });

        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        log.info("=======网关配置信息===加载完成======");
        return "success";
    }


    public void saveRoute(GatewayRoute gatewayRoute){
        RouteDefinition definition=handleData(gatewayRoute);
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public Flux<RouteDefinition> getRoute(){
        return routeDefinitionWriter.getRouteDefinitions();
    }


    public void update(GatewayRoute gatewayRoute) {
        RouteDefinition definition=handleData(gatewayRoute);
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void deleteRoute(String routeId){
        routeDefinitionWriter.delete(Mono.just(routeId)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }


    /**
     * 路由数据转换公共方法
     * @param gatewayRoute
     * @return
     */
    private RouteDefinition handleData(GatewayRoute gatewayRoute){
        RouteDefinition definition = new RouteDefinition();
        definition.setId(gatewayRoute.getServiceId());
        definition.setPredicates(JSON.parseArray(gatewayRoute.getPredicates(),PredicateDefinition.class));
        definition.setFilters(JSON.parseArray(gatewayRoute.getFilters(),FilterDefinition.class));
        definition.setUri(UriComponentsBuilder.fromHttpUrl(gatewayRoute.getUri()).build().toUri());
        definition.setOrder(gatewayRoute.getOrderNum());
        return definition;
    }

}

