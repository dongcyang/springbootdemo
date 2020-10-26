package com.dcy.springbootrabbitmqconsumer.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcy.springbootrabbitmqconsumer.config.RabbitMQConfig;
import com.dcy.springbootrabbitmqconsumer.mapper.UserMapper;
import com.dcy.springbootrabbitmqconsumer.pojo.User;

import com.dcy.springbootrabbitmqconsumer.pojo.Knowledge;
import com.dcy.springbootrabbitmqconsumer.search.pojo.KnowledgeEs;
import com.dcy.springbootrabbitmqconsumer.search.service.KnowledgeService;
import com.dcy.springbootrabbitmqconsumer.service.KmService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @ClassName SaveUserListener
 * @Description //自定义RabbitMQ监听器，使用ack确认机制手动回复消息
 * @Author Mr.Dong
 * @Date 2020/10/24 15:59
 * @Version 1.0
 */
@Component
public class SaveUserListener {

  @Autowired
    UserMapper userMapper;
  @Autowired
  private KmService kmService;

  @Autowired
  private KnowledgeService knowledgeService;

    @RabbitListener(queues = RabbitMQConfig.USER_QUEUE)
    public void getMessage(Channel channel, Message message){
        try {
            channel.basicQos(300);//设置同时可抓取300个消息
            //接收消息，把User对象信息存入到数据库中
            byte[] userBytes = message.getBody();
            User user = JSON.parseObject(userBytes,User.class);
            int count = userMapper.addUser(user);
            //返回成功或者失败消息
            if (count<0){
                //失败
                try {
                    /*
                     * 第一个boolean类型： true:所有消费者都会拒绝这个消息，false代表只有当前消费者拒绝
                     * 第二个boolean类型： true:当前消息会进入死信队列， false重新回到原有队列中，默认回到头部
                     */
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    /*
                     * 第一个参数： message.getMessageProperties().getDeliveryTag() 是通道号加上消息编号，全局唯一。
                     * 第二个boolean类型： false:如果有多个消费方微服务，则只有当前消费者微服务返回成功消费消息。
                     */
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            //失败
            try {
                /*
                 * 第一个boolean类型： true:所有消费者都会拒绝这个消息，false代表只有当前消费者拒绝
                 * 第二个boolean类型： true:当前消息会进入死信队列， false重新回到原有队列中，默认回到头部
                 */
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    /**
     * @Author Mr.Dong
     * @Description //向Es中添加知识数据根据RabiitMQ中的“km_queue”队列中的知识id
     * @Date 18:39 2020/10/26
     * @Param
     * @return
     **/
    @RabbitListener(queues = RabbitMQConfig.KM_QUEUE)
    public void getKmIdMessage(Channel channel, Message message){
        try {
            channel.basicQos(300);//设置同时可抓取300个消息
            //接收消息，把对象信息存入到数据库中
            String kmId = new String(message.getBody());
            //根据知识Id,去数据库中查询知识数据
            Knowledge knowledge = kmService.selectKmById(Long.valueOf(kmId));
            //返回成功或者失败消息
            if (StringUtils.isEmpty(knowledge)){
                //失败
                try {
                    /*
                     * 第一个boolean类型： true:所有消费者都会拒绝这个消息，false代表只有当前消费者拒绝
                     * 第二个boolean类型： true:当前消息会进入死信队列， false重新回到原有队列中，默认回到头部
                     */
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    String jsKm = JSON.toJSONString(knowledge);
                    KnowledgeEs knowledgeEs = JSONObject.parseObject(jsKm, KnowledgeEs.class);
                    //把查询出来的知识信息导入到ES索引库中
                   knowledgeService.addKnowledgeToEs(knowledgeEs);
                    /*
                     * 第一个参数： message.getMessageProperties().getDeliveryTag() 是通道号加上消息编号，全局唯一。
                     * 第二个boolean类型： false:如果有多个消费方微服务，则只有当前消费者微服务返回成功消费消息。
                     */
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            //失败
            try {
                /*
                 * 第一个boolean类型： true:所有消费者都会拒绝这个消息，false代表只有当前消费者拒绝
                 * 第二个boolean类型： true:当前消息会进入死信队列， false重新回到原有队列中，默认回到头部
                 */
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
