package com.bz.gateway.dynamicroute.controller;

import com.bz.gateway.dynamicroute.entity.GatewayRoute;
import com.bz.gateway.dynamicroute.entity.dto.BaseResponse;
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

    @RequestMapping("/redis/routes")
    public Flux<RouteDefinition> routes(){
        return gatewayServiceHandler.getRoute();
    }

    @RequestMapping("/routes")
    public BaseResponse route(){
        return BaseResponse.success(gatewayRouteService.list());
    }

    /**
     * 刷新路由配置
     * @return
     */
    @GetMapping("/refresh")
    public BaseResponse refresh() {
        return BaseResponse.success(this.gatewayServiceHandler.loadRouteConfig());
    }

    /**
     * 增加路由记录
     * @param gatewayRouteDto
     * @return
     */
    @PostMapping("/add")
    public BaseResponse add(@RequestBody GatewayRouteDto gatewayRouteDto) {
        gatewayRouteService.add(gatewayRouteDto);
        GatewayRoute gatewayRoute=new GatewayRoute();
        BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
        gatewayServiceHandler.saveRoute(gatewayRoute);
        return BaseResponse.success();
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody GatewayRouteDto gatewayRouteDto) {
        gatewayRouteService.update(gatewayRouteDto);
        GatewayRoute gatewayRoute=new GatewayRoute();
        BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
        gatewayServiceHandler.update(gatewayRoute);
        return BaseResponse.success();
    }

    @GetMapping("/delete")
    public BaseResponse delete(String id) {
        gatewayRouteService.delete(Integer.parseInt(id));
        gatewayServiceHandler.deleteRoute(id);
        return BaseResponse.success();
    }

    @GetMapping("/getById")
    public BaseResponse getById(Integer id){
        return BaseResponse.success(gatewayRouteService.getById(id));
    }
}
