package com.dcy.springbootprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//开启Eureka客户端
public class SpringbootproviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootproviderApplication.class, args);
    }

}
