package com.dcy.springbootprovider.controller;


import com.dcy.springbootprovider.domain.User;
import com.dcy.springbootprovider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/20 12:36
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Value("${server.port}")
    int port;


    @RequestMapping("/findById")
    public User findById(Long id){
//        模拟熔断器请求超时
    /*    try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        User user = userService.findById(id);
        user.setAge(port);
        System.out.println("端口号为："+port);
        return user;

    }

}
