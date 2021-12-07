package com.bz.gateway.dynamicroute.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRoute {

    private Integer id;

    private String serviceId;

    private String uri;

    private String predicates;

    private String filters;

    private Integer orderNum;

    private String creatorId;

    private Date createDate;

    private String updateId;

    private Date updateDate;

    private String remarks;

    private String delFlag;

}
