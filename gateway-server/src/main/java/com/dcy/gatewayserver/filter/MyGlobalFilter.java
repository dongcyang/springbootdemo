package com.dcy.gatewayserver.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName MyGlobalFilter
 * @Description 自定义全局过滤器，模拟请求登录效验
 * @Author Mr.Dong
 * @Date 2020/10/20 18:47
 * @Version 1.0
 */
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
//        获取请求参数中的token信息，如果没有，则验证不通过返回错误消息
        String token = queryParams.getFirst("token");
        if (StringUtils.isEmpty(token)){
            //说明为空，没有token信息
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);//设置状态码
            return response.setComplete();
        }
            //存在
//           请求放行
            return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
