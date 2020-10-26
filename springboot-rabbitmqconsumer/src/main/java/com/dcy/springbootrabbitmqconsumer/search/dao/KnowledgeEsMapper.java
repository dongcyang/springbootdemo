package com.dcy.springbootrabbitmqconsumer.search.dao;

import com.dcy.springbootrabbitmqconsumer.search.pojo.KnowledgeEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ClassName KnowledgeEsMapper
 * @Description 调用springDataEs索引库
 * @Author Mr.Dong
 * @Date 2020/10/26 12:50
 * @Version 1.0
 */
public interface KnowledgeEsMapper extends ElasticsearchRepository<KnowledgeEs,Long> {

}
