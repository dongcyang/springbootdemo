package com.dcy.springbootrabbitmqconsumer.routingqueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName RoutingQueueDemo1
 * @Description 路由模式监听器一
 * @Author Mr.Dong
 * @Date 2020/10/21 15:07
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "direct_queue_insert")
public class RoutingQueueDemo1 {

    @RabbitHandler
    public void getRoutingListenerMessage1(String message){
        System.out.println("======路由模式监听器insert:"+message);
    }
}
