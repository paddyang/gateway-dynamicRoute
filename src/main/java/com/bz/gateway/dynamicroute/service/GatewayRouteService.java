package com.bz.gateway.dynamicroute.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bz.gateway.dynamicroute.config.GatewayServiceHandler;
import com.bz.gateway.dynamicroute.entity.GatewayRoute;
import com.bz.gateway.dynamicroute.entity.dto.GatewayRouteDto;
import com.bz.gateway.dynamicroute.mapper.GatewayRouteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GatewayRouteService {

    private final GatewayRouteMapper gatewayRouteMapper;
    private final GatewayServiceHandler gatewayServiceHandler;


    public Integer add(GatewayRouteDto gatewayRouteDto) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
        gatewayServiceHandler.saveRoute(gatewayRoute);
        gatewayRoute.setCreateDate(new Date());
        gatewayRoute.setCreatorId("");
        gatewayRoute.setDelFlag("0");
        return gatewayRouteMapper.insert(gatewayRoute);
    }


    public Integer update(GatewayRouteDto gatewayRouteDto) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
        gatewayServiceHandler.update(gatewayRoute);
        gatewayRoute.setUpdateDate(new Date());
        gatewayRoute.setUpdateId("");
        return gatewayRouteMapper.updateById(gatewayRoute);
    }


    public Integer delete(String id) {
        gatewayServiceHandler.deleteRoute(id);
        GatewayRoute route = new GatewayRoute();
        route.setId(Integer.parseInt(id));
        route.setDelFlag("1");
        return gatewayRouteMapper.updateById(route);
    }


    public List<GatewayRoute> list() {
        QueryWrapper<GatewayRoute> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag",0);
        wrapper.orderByAsc("order_num");
        return gatewayRouteMapper.selectList(wrapper);
    }

    public GatewayRoute getById(Integer id) {
        return gatewayRouteMapper.selectById(id);
    }

    public Flux<RouteDefinition> getCacheRoute() {
        return gatewayServiceHandler.getRoute();
    }

    public String refresh() {
        return gatewayServiceHandler.loadRouteConfig();
    }
}
