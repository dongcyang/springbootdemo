package com.dcy.springbootrabbitmqconsumer.workqueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName WorkQueueDemo2
 * @Description Work模式监听器二
 * @Author Mr.Dong
 * @Date 2020/10/21 14:27
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "work_queue")
public class WorkQueueDemo2 {

    @RabbitHandler
    public void getWorkListener2(String message){
        System.out.println("======Work模式接收消息2："+message);
    }
}
