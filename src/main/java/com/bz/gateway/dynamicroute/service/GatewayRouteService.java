package com.bz.gateway.dynamicroute.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bz.gateway.dynamicroute.entity.GatewayRoute;
import com.bz.gateway.dynamicroute.entity.dto.GatewayRouteDto;
import com.bz.gateway.dynamicroute.mapper.GatewayRouteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GatewayRouteService {

    private final GatewayRouteMapper gatewayRouteMapper;

    public Integer add(GatewayRouteDto gatewayRouteDto) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
        gatewayRoute.setCreateDate(new Date());
        gatewayRoute.setCreatorId("");
        return gatewayRouteMapper.insert(gatewayRoute);
    }


    public Integer update(GatewayRouteDto gatewayRouteDto) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
        gatewayRoute.setUpdateDate(new Date());
        gatewayRoute.setUpdateId("");
        return gatewayRouteMapper.updateById(gatewayRoute);
    }


    public Integer delete(Integer id) {
        GatewayRoute route = new GatewayRoute();
        route.setId(id);
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
}
