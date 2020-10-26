package com.dcy.springbootrabbitmqconsumer.search.controller;

import com.dcy.springbootrabbitmqconsumer.search.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName KMController
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/26 13:01
 * @Version 1.0
 */
@RestController
@RequestMapping("/search")
public class KMController {

    @Autowired
    KnowledgeService knowledgeService;


    /**
     * @Author Mr.Dong
     * @Description //创建索引库
     * @Date 13:04 2020/10/26
     * @Param []
     * @return void
     **/
    @RequestMapping("/import")
    public String importEs(){
        knowledgeService.createEsIndexAndMapping();
        return "创建KM索引库成功!!!!";
    }


}
