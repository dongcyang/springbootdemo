package com.dcy.springbootconsumer.service;

import com.dcy.springbootconsumer.domain.User;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserServiceImpl
 * @Description FeignClient客户端接口的Hystrix实现类
 * @Author Mr.Dong
 * @Date 2020/10/20 19:58
 * @Version 1.0
 */
@Component
public class UserServiceImpl implements UserService {
    @Override
    public User findById(Long id) {
        User user=new User();
        user.setAge(15);
        user.setName("当前用户信息不存在");
        user.setSex("男");
        return user;
    }
}
