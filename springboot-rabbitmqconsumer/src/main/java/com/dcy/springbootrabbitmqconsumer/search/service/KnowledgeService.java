package com.dcy.springbootrabbitmqconsumer.search.service;

import com.dcy.springbootrabbitmqconsumer.search.pojo.KnowledgeEs;

import java.util.List;
import java.util.Map;

/**
 * @ClassName KnowledgeService
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/26 12:49
 * @Version 1.0
 */
public interface KnowledgeService {


    void createEsIndexAndMapping();

    /**
     * @Author Mr.Dong
     * @Description //新增
     * @Date 12:57 2020/10/26
     * @Param []
     * @return void
     **/
    public void addKnowledgeToEs(KnowledgeEs knowledge);

    /**
     * @Author Mr.Dong
     * @Description //批量新增（数据全量导入）
     * @Date 12:57 2020/10/26
     * @Param []
     * @return void
     **/
    public void addKmListToEs(List<KnowledgeEs> kmList);

    /**
     * @Author Mr.Dong
     * @Description //简单查询ES库
     * @Date 21:47 2020/10/26
     * @Param [paramMap]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String,Object> simpleSearch(Map<String,String> paramMap);

    }
