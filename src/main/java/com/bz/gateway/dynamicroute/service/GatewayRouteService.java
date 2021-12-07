package com.bz.gateway.dynamicroute.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bz.gateway.dynamicroute.entity.GatewayRoute;
import com.bz.gateway.dynamicroute.entity.dto.GatewayRouteDto;
import com.bz.gateway.dynamicroute.mapper.GatewayRouteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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


    public Integer delete(String id) {
        return gatewayRouteMapper.deleteById(Long.parseLong(id));
    }


    public List<GatewayRoute> list() {
        QueryWrapper<GatewayRoute> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag",0);
        return gatewayRouteMapper.selectList(wrapper);
    }
}
