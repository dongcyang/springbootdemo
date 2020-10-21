package com.dcy.springbootrabbitmqconsumer.psqueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName PSQueueDemo
 * @Description 发布订阅模式接收队列消息
 * @Author Mr.Dong
 * @Date 2020/10/21 14:47
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "ps_queue1")
public class PSQueueDemo {

    @RabbitHandler
    public void getPSMessage1(String message){
        System.out.println("=========发布订阅模式队列一："+message);
    }
}
