package com.dcy.springbootrabbitmqprovider.service.impl;

import com.dcy.springbootrabbitmqprovider.mapper.KnowledgeMapper;
import com.dcy.springbootrabbitmqprovider.pojo.Knowledge;
import com.dcy.springbootrabbitmqprovider.service.KmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName KmServiceImpl
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/26 13:31
 * @Version 1.0
 */
@Service
public class KmServiceImpl implements KmService {


    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Override
    public int addKnowledge(Knowledge knowledge) {
        int kmid = knowledgeMapper.addKnowledge(knowledge);
        return kmid;
    }
}
