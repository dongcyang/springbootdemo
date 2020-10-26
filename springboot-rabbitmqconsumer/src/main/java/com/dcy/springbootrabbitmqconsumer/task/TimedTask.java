package com.dcy.springbootrabbitmqconsumer.task;

import com.alibaba.fastjson.JSON;
import com.dcy.springbootrabbitmqconsumer.pojo.Knowledge;
import com.dcy.springbootrabbitmqconsumer.search.pojo.KnowledgeEs;
import com.dcy.springbootrabbitmqconsumer.search.service.KnowledgeService;
import com.dcy.springbootrabbitmqconsumer.service.KmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TimedTask
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/26 20:59
 * @Version 1.0
 */
@Component
public class TimedTask {

    @Autowired
    private KmService kmService;

    @Autowired
    private KnowledgeService knowledgeService;

    //定时全量导入知识到ES索引库
    @Scheduled(cron = "0 18 21 * * ?")
    public void kmTask(){
        List<Knowledge> knowledges = kmService.selectAllKm();
        ArrayList<KnowledgeEs> kmList = new ArrayList<>();
        for (Knowledge knowledge : knowledges) {
            String km = JSON.toJSONString(knowledge);
            KnowledgeEs knowledgeEs = JSON.parseObject(km, KnowledgeEs.class);
            kmList.add(knowledgeEs);
        }
        knowledgeService.addKmListToEs(kmList);
    }
}
