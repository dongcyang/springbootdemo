package com.dcy.springbootrabbitmqprovider.myrabbitmqsend;


import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName ProviderMessageSender
 * @Description 自定义RabbitMQ消息发送类，从而实现RabbitMQ的Confirm机制
 * @Author Mr.Dong
 * @Date 2020/10/24 14:22
 * @Version 1.0
 */
@Component
public class ProviderMessageSender implements RabbitTemplate.ConfirmCallback {

    private static final String MESSAGE_CONFIRM="message_confirm";

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    public ProviderMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate=rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * @Author Mr.Dong
     * @Description //重写消息回滚方法
     * @Date 14:24 2020/10/24
     * @Param [correlationData, b, s]
     * @return void
     **/
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String message) {
         if (ack){
             //说明发送消息成功接收
             //删除Redis中的相关数据
             redisTemplate.delete(correlationData.getId());
             redisTemplate.delete(MESSAGE_CONFIRM+correlationData.getId());
         }else {
             //返回失败通知，重新发送消息
             Map<String,String> map= (Map<String,String>)redisTemplate.opsForHash().entries(MESSAGE_CONFIRM+correlationData.getId());
             String exchange = map.get("exchange");
             String routingKey = map.get("routingKey");
             String sendMessage = map.get("sendMessage");

             //重新发送消息
             rabbitTemplate.convertAndSend(exchange,routingKey, JSON.toJSONString(sendMessage),correlationData);
         }

    }
    /**
     * @Author Mr.Dong
     * @Description //自定义发送方法
     * @Date 14:36 2020/10/24
     * @Param [exchange, routingKey, message]
     * @return void
     **/
    public  void sendMessage(String exchange,String routingKey,String message){
        //设置消息的唯一标识，并存入到redis缓存中
        CorrelationData correlationData=new CorrelationData(UUID.randomUUID().toString());
        redisTemplate.opsForValue().set(correlationData.getId(),message);

        //消息的相关元信息存入到缓存
        Map<String,String> map=new HashMap<>();
        map.put("exchange",exchange);
        map.put("routingKey",routingKey);
        map.put("sendMessage",message);
        redisTemplate.opsForHash().putAll(MESSAGE_CONFIRM+correlationData.getId(),map);

//        携带消息唯一标识信息发送消息
        rabbitTemplate.convertAndSend(exchange,routingKey,message,correlationData);
    }
}
