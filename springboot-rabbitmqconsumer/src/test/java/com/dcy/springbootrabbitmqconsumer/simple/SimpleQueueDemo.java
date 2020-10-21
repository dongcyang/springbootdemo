package com.dcy.springbootrabbitmqconsumer.simple;

import com.dcy.springbootrabbitmqconsumer.SpringbootRabbitmqconsumerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName SimpleQueueDemo
 * @Description 消费者接收RabbitMQ指定队列的消息
 * @Author Mr.Dong
 * @Date 2020/10/21 14:10
 * @Version 1.0
 */
@SpringBootTest(classes = SpringbootRabbitmqconsumerApplication.class)
@RunWith(SpringRunner.class)
public class SimpleQueueDemo {

}
