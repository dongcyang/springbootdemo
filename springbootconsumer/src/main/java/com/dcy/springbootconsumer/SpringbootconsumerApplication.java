package com.dcy.springbootconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableDiscoveryClient//Eureka客户端
//@EnableCircuitBreaker//开启熔断器
@SpringCloudApplication
@EnableFeignClients //开启Feign远程调用
public class SpringbootconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootconsumerApplication.class, args);
    }
//   @Bean
//   @LoadBalanced//开启负载均衡（默认走的是轮询算法）
//    public RestTemplate restTemplate(){
//       return new RestTemplate();
//   }
}
