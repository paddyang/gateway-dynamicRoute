package com.bz.gateway.dynamicroute.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Component
public class LoginFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //log.info("收到{}请求：{}",request.getMethodValue(), request.getURI());
        if (!request.getPath().toString().startsWith("/route")){
            return chain.filter(exchange);
        }
        return exchange.getSession().flatMap(session->{
            String username = session.getAttribute("username");
            if (StringUtils.isBlank(username)){
                //拼接登陆页面路径
                String rpuri = request.getURI().getScheme() +
                        "://" +
                        request.getURI().getAuthority() +
                        "/mgr/expire";
                URI uri = UriComponentsBuilder.fromHttpUrl(rpuri).build().toUri();
                //exchange.mutate().response("").build();
                //创建返回值
                ServerWebExchange withChangedUri = exchange.mutate().request(builder -> {
                    builder.uri(uri);
                }).build();

                //返回登陆页面
                return chain.filter(withChangedUri);
            }
            return chain.filter(exchange);
        });
    }

}
