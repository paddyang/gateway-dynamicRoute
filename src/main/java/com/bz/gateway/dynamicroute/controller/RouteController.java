package com.bz.gateway.dynamicroute.controller;

import com.bz.gateway.dynamicroute.entity.dto.BaseResponse;
import com.bz.gateway.dynamicroute.entity.dto.GatewayRouteDto;
import com.bz.gateway.dynamicroute.service.GatewayRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
public class RouteController {

    private final GatewayRouteService gatewayRouteService;

    @RequestMapping("/redis/routes")
    public Flux<RouteDefinition> routes(){
        return gatewayRouteService.getCacheRoute();
    }

    @RequestMapping("/routes")
    public BaseResponse route(){
        return BaseResponse.success(gatewayRouteService.list());
    }

    /**
     * 刷新路由配置
     */
    @GetMapping("/refresh")
    public BaseResponse refresh() {
        return BaseResponse.success(gatewayRouteService.refresh());
    }

    /**
     * 增加路由记录
     */
    @PostMapping("/add")
    public BaseResponse add(@RequestBody GatewayRouteDto gatewayRouteDto) {
        gatewayRouteService.add(gatewayRouteDto);
        return BaseResponse.success();
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody GatewayRouteDto gatewayRouteDto) {
        gatewayRouteService.update(gatewayRouteDto);
        return BaseResponse.success();
    }

    @GetMapping("/delete")
    public BaseResponse delete(String id) {
        gatewayRouteService.delete(id);
        return BaseResponse.success();
    }

    @GetMapping("/getById")
    public BaseResponse getById(Integer id){
        return BaseResponse.success(gatewayRouteService.getById(id));
    }
}
