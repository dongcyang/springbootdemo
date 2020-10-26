package com.dcy.springbootrabbitmqprovider.mapper;


import com.dcy.springbootrabbitmqprovider.pojo.Knowledge;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * @ClassName KnowledgeMapper
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/24 15:47
 * @Version 1.0
 */
@Repository
@Mapper
public interface KnowledgeMapper {


    int addKnowledge(Knowledge knowledge);
}
