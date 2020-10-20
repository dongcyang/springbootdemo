package com.dcy.springbootprovider.service;

import com.dcy.springbootprovider.domain.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/20 13:06
 * @Version 1.0
 */
public interface UserService {


    /**
     * @Author Mr.Dong
     * @Description //根据用户id查询用户信息
     * @Date 12:39 2020/10/20
     * @Param [id]
     * @return com.dcy.springbootprovider.domain.User
     **/
    User findById(Long id);
}
