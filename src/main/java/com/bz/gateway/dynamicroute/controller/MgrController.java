package com.bz.gateway.dynamicroute.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bz.gateway.dynamicroute.entity.dto.BaseResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;

@RestController
@RequestMapping("/mgr")
public class MgrController {

    @RequestMapping( "/login")
    public BaseResponse login(String username, String password, WebSession session) {
        String u="admin";
        String p="5320";
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return BaseResponse.build(400, "用户名或者密码不能为空！");
        }
        if (u.equals(username) && p.equals(password)) {
            session.getAttributes().put("username",username);
            return BaseResponse.success();
        }
        return BaseResponse.build(400, "用户名或者密码错误！");
    }


} 