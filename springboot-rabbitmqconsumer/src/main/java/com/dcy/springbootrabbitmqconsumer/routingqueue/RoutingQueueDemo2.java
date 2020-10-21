package com.dcy.springbootrabbitmqconsumer.routingqueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName RoutingQueueDemo1
 * @Description 路由模式监听器二
 * @Author Mr.Dong
 * @Date 2020/10/21 15:07
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "direct_queue_update")
public class RoutingQueueDemo2 {

    @RabbitHandler
    public void getRoutingListenerMessage2(String message){
        System.out.println("======路由模式监听器update:"+message);
    }
}
