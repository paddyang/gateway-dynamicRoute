package com.bz.gateway.dynamicroute.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRouteDto {

    private Integer id;

    private String serviceId;

    private String uri;

    private String predicates;

    private String filters;

    private Integer orderNum;

    private String remarks;
}
