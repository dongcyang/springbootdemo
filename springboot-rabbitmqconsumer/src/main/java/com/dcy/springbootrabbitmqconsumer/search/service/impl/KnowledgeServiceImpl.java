package com.dcy.springbootrabbitmqconsumer.search.service.impl;

import com.dcy.springbootrabbitmqconsumer.search.dao.KnowledgeEsMapper;
import com.dcy.springbootrabbitmqconsumer.search.pojo.KnowledgeEs;
import com.dcy.springbootrabbitmqconsumer.search.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName KnowledgeServiceImpl
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/26 12:49
 * @Version 1.0
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {


    @Autowired
      private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private KnowledgeEsMapper knowledgeEsMapper;


/**
 * @Author Mr.Dong
 * @Description //创建knowledge索引库
 * @Date 12:55 2020/10/26
 * @Param []
 * @return void
 **/
    @Override
    public void createEsIndexAndMapping() {
//        创建索引库
        elasticsearchTemplate.createIndex(KnowledgeEs.class);
//        创建mapping映射配置
       elasticsearchTemplate.putMapping(KnowledgeEs.class);
    }
/**
 * @Author Mr.Dong
 * @Description //新增
 * @Date 12:57 2020/10/26
 * @Param []
 * @return void
 **/
    public void addKnowledgeToEs(KnowledgeEs knowledge){
        knowledgeEsMapper.save(knowledge);
    }
    /**
     * @Author Mr.Dong
     * @Description //批量新增（数据全量导入）
     * @Date 12:57 2020/10/26
     * @Param []
     * @return void
     **/
    public void addKmListToEs(List<KnowledgeEs> kmList){
        knowledgeEsMapper.saveAll(kmList);
    }
}
