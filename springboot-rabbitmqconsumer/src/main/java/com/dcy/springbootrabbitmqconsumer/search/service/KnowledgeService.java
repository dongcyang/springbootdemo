package com.dcy.springbootrabbitmqconsumer.search.service;

import com.dcy.springbootrabbitmqconsumer.search.pojo.KnowledgeEs;

import java.util.List;

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

    }
