package com.dcy.springbootrabbitmqconsumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName CreateQueue
 * @Description //自定义配置queue队列bean类
 * @Author Mr.Dong
 * @Date 2020/10/24 15:16
 * @Version 1.0
 */
@Configuration
public class RabbitMQConfig {

    public static  final String USER_QUEUE="user_queue";

    /**
     * @Author Mr.Dong
     * @Description /声明队列，并开启持久化
     * @Date 15:24 2020/10/24
     * @Param []
     * @return org.springframework.amqp.core.Queue
     **/
    @Bean
    public Queue queue(){
        //参数一：队列名称
        //参数二：是否开启队列持久化
        return new Queue(USER_QUEUE,true);
    }
}
