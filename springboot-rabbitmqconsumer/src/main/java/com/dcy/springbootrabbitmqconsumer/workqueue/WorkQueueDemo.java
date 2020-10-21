package com.dcy.springbootrabbitmqconsumer.workqueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName WorkQueueDemo
 * @Description 工作模式监听器一
 * @Author Mr.Dong
 * @Date 2020/10/21 14:24
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "work_queue")
public class WorkQueueDemo {

    @RabbitHandler
    public void getWorkListener1(String message){
        System.out.println("======Work模式接收消息1："+message);
    }
}
