package com.dcy.springbootrabbitmqprovider.simplequeue;

import com.alibaba.fastjson.JSON;
import com.dcy.springbootrabbitmqprovider.config.RabbitMQConfig;
import com.dcy.springbootrabbitmqprovider.myrabbitmqsend.ProviderMessageSender;
import com.dcy.springbootrabbitmqprovider.pojo.User;
import com.dcy.springbootrabbitmqprovider.SpringbootRabbitmqproviderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @ClassName SimpleQueueDemo
 * @Description 练习RabbitMQ生产者的简单模式
 * @Author Mr.Dong
 * @Date 2020/10/21 13:56
 * @Version 1.0
 */
@SpringBootTest(classes = SpringbootRabbitmqproviderApplication.class)
@RunWith(SpringRunner.class)
public class SimpleQueueDemo {
     @Autowired
    private RabbitTemplate rabbitTemplate;
/**
 * @Author Mr.Dong
 * @Description // 简单模式
 * @Date 14:29 2020/10/21
 * @Param []
 * @return void
 **/
     @Test
    public void testHelloWordSend(){
         rabbitTemplate.convertAndSend("simple_queue","你好啊，小浣熊。。");
     }
/**
 * @Author Mr.Dong
 * @Description //工作模式
 * @Date 14:29 2020/10/21
 * @Param []
 * @return void
 **/
     @Test
    public void testWorkSend(){
         for (int i = 0; i < 100; i++) {
         rabbitTemplate.convertAndSend("work_queue","你好啊，小涵涵。。"+i);
         }
     }
     /**
      * @Author Mr.Dong
      * @Description //P/S发布订阅模式
      * @Date 14:40 2020/10/21
      * @Param []
      * @return void
      **/
     @Test
    public void PSSend(){
         for (int i = 0; i < 100; i++) {
             rabbitTemplate.convertAndSend("fanout_exchange","","你好啊，小喵喵。。"+i);
         }
     }
     /**
      * @Author Mr.Dong
      * @Description //Routing路由模式
      * @Date 15:04 2020/10/21
      * @Param []
      * @return void
      **/
    @Test
    public void RoutingSend(){
        for (int i = 0; i < 100; i++) {
            if (i%2==0){

            rabbitTemplate.convertAndSend("direct_exchange","insertKey","你好啊，小喵喵。。"+i);
            }else {
                rabbitTemplate.convertAndSend("direct_exchange","updateKey","你不太好啊，小喵喵。。"+i);
            }
        }
    }

    /**
     * @Author Mr.Dong
     * @Description //Topic主题模式
     * @Date 15:04 2020/10/21
     * @Param []
     * @return void
     **/
    @Test
    public void TopicSend(){
                rabbitTemplate.convertAndSend("topic_exchange","hello.word","你好啊，小改改。。");
                rabbitTemplate.convertAndSend("topic_exchange","hello.word.you","你不太好啊，小改改。。");
    }
/**
 * @Author Mr.Dong
 * @Description //使用RabbitMQ的confirm机制发送消息
 * @Date 15:13 2020/10/24
 * @Param []
 * @return void
 **/
@Autowired
ProviderMessageSender providerMessageSender;
    @Test
    public void sendConfirm(){
            User user=new User();
            user.setUsername("RabbitMQConfirm机制");
            user.setPassword(UUID.randomUUID().toString());
            user.setAge(20);
            String jsonUser = JSON.toJSONString(user);
            providerMessageSender.sendMessage("", RabbitMQConfig.USER_QUEUE,jsonUser);
    }
}
