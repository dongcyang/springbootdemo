package com.dcy.springbootconsumer.log;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyConfiguration
 * @Description 创建日志配置类
 * @Author Mr.Dong
 * @Date 2020/10/20 20:05
 * @Version 1.0
 */
@Component
public class MyConfiguration {



    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
