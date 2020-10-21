package com.dcy.springbootrabbitmqconsumer.topicqueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName TopicQueueDemo1
 * @Description 主题模式监控一
 * @Author Mr.Dong
 * @Date 2020/10/21 15:21
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "topic_queue1")
public class TopicQueueDemo1 {


    @RabbitHandler
    public void getTopicListenerMessage(String message){
        System.out.println("==========主题模式监控hello.*:"+message);
    }
}
