package com.dcy.springbootrabbitmqconsumer.service.impl;

import com.dcy.springbootrabbitmqconsumer.mapper.KnowledgeMapper;
import com.dcy.springbootrabbitmqconsumer.pojo.Knowledge;
import com.dcy.springbootrabbitmqconsumer.service.KmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName KmServiceImpl
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/26 18:48
 * @Version 1.0
 */
@Service
public class KmServiceImpl implements KmService {

    @Autowired
    private KnowledgeMapper knowledgeMapper;


    @Override
    public Knowledge selectKmById(Long kmId) {
        return knowledgeMapper.selectKmById(kmId);
    }

    public List<Knowledge> selectAllKm(){
        return knowledgeMapper.selectAllKm();
    }
}
