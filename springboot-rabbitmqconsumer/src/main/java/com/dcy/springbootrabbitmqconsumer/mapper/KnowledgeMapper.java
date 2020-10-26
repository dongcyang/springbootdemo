package com.dcy.springbootrabbitmqconsumer.mapper;

import com.dcy.springbootrabbitmqconsumer.pojo.Knowledge;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName KnowledgeEsMapper
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/26 18:49
 * @Version 1.0
 */
@Repository
@Mapper
public interface KnowledgeMapper {

    Knowledge selectKmById(Long kmId);

    List<Knowledge> selectAllKm();
}
