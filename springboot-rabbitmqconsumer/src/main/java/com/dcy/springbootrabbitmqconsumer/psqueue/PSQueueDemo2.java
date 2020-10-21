package com.dcy.springbootrabbitmqconsumer.psqueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName PSQueueDemo2
 * @Description 发布订阅模式监听器监听队列二
 * @Author Mr.Dong
 * @Date 2020/10/21 14:49
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "ps_queue2")
public class PSQueueDemo2 {

    @RabbitHandler
    public void getPSMessage2(String  message){
        System.out.println("=========发布订阅模式队列二："+message);
    }
}
