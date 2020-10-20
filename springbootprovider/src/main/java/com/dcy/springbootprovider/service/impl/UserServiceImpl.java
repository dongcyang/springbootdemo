package com.dcy.springbootprovider.service.impl;

import com.dcy.springbootprovider.mapper.UserMapper;
import com.dcy.springbootprovider.domain.User;
import com.dcy.springbootprovider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/20 13:06
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }
}
