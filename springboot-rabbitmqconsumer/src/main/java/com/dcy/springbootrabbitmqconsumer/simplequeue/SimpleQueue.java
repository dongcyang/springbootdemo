package com.dcy.springbootrabbitmqconsumer.simplequeue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName SimpleQueue
 * @Description 消费者监听RabbitMQ的指定队列的消息
 * @Author Mr.Dong
 * @Date 2020/10/21 14:16
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "simple_queue")
public class SimpleQueue {

    @RabbitHandler
    public void getListenerMessage(String message){
        System.out.println("======接收到的消息HelloWord模式为：====="+message);
    }
}
