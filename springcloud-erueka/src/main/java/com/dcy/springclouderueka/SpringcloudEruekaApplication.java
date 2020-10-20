package com.dcy.springclouderueka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer//开启Eureka服务
public class SpringcloudEruekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEruekaApplication.class, args);
    }

}
