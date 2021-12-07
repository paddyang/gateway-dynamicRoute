package com.bz.gateway.dynamicroute.controller;

import com.bz.gateway.dynamicroute.entity.GatewayRoute;
import com.bz.gateway.dynamicroute.entity.dto.GatewayRouteDto;
import com.bz.gateway.dynamicroute.service.GatewayRouteService;
import com.bz.gateway.dynamicroute.config.GatewayServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
public class RouteController {

    private final GatewayServiceHandler gatewayServiceHandler;
    private final GatewayRouteService gatewayRouteService;

    @RequestMapping("/routes")
    public Flux<RouteDefinition> routes(){
        return gatewayServiceHandler.getRoute();
    }

    /**
     * 刷新路由配置
     * @return
     */
    @GetMapping("/refresh")
    public String refresh() {
        return this.gatewayServiceHandler.loadRouteConfig();
    }

    /**
     * 增加路由记录
     * @param gwdefinition
     * @return
     */
    @PostMapping("/add")
    public String add(@RequestBody GatewayRouteDto gatewayRouteDto) {
        gatewayRouteService.add(gatewayRouteDto);
        GatewayRoute gatewayRoute=new GatewayRoute();
        BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
        gatewayServiceHandler.saveRoute(gatewayRoute);
        return "success";
    }

    @PostMapping("/update")
    public String update(@RequestBody GatewayRouteDto gatewayRouteDto) {
        gatewayRouteService.update(gatewayRouteDto);
        GatewayRoute gatewayRoute=new GatewayRoute();
        BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
        gatewayServiceHandler.update(gatewayRoute);
        return "success";
    }

    @GetMapping("/delete")
    public String delete(@PathVariable String id) {
        gatewayRouteService.delete(id);
        gatewayServiceHandler.deleteRoute(id);
        return "success";
    }

}
