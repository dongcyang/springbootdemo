package com.dcy.springbootrabbitmqprovider.controller;

import com.alibaba.fastjson.JSON;
import com.dcy.springbootrabbitmqprovider.config.RabbitMQConfig;
import com.dcy.springbootrabbitmqprovider.myrabbitmqsend.ProviderMessageSender;
import com.dcy.springbootrabbitmqprovider.pojo.Knowledge;
import com.dcy.springbootrabbitmqprovider.service.KmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName KmController
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/26 13:15
 * @Version 1.0
 */
@RestController
@RequestMapping("/provider")
public class KmController {

    @Autowired
    private KmService kmService;

    @Autowired
    private ProviderMessageSender providerMessageSender;

    @RequestMapping("/addKm")
    public String addKm(){
        //模拟从前端传过来了知识详情信息。

            Knowledge knowledge=new Knowledge();
            knowledge.setKnowledgename("测试知识上传ssadasdsa");
            knowledge.setKeyword("测试");
            knowledge.setSummer("测试知识就问你六不六，牛不牛逼，卡拉斯不克拉斯");
            knowledge.setVersion(1);
            knowledge.setParam4(2);
            knowledge.setParam5(0);
            int count = kmService.addKnowledge(knowledge);
       if (count==0){
            //说明添加失败
            return "添加知识失败！！";
        }else {
            //说明添加成功
//            获取知识id,将知识id发送到“km_queue”队列中。
//            knowledge.setKnowledgeid(Long.valueOf(kmid));
//           使用fastjson将对象转换成json字符串
//            String kmStr = JSON.toJSONString(knowledge);
            providerMessageSender.sendMessage("", RabbitMQConfig.KM_QUEUE,String.valueOf(knowledge.getId()));
            return "添加知识成功！！";
        }

    }
}
