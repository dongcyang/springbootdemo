package com.dcy.springbootrabbitmqconsumer.service;

import com.dcy.springbootrabbitmqconsumer.pojo.Knowledge;

import java.util.List;

/**
 * @ClassName KmService
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/26 18:47
 * @Version 1.0
 */
public interface  KmService {


   Knowledge selectKmById(Long kmId);

   List<Knowledge> selectAllKm();
}
