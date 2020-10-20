package com.dcy.springbootconsumer.controller;

import com.dcy.springbootconsumer.domain.User;
import com.dcy.springbootconsumer.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/20 12:21
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
//配置全局熔断降级（只针对于有@HystrixCommand注解的方法有效）
@DefaultProperties(defaultFallback = "defaultFallback")
public class UserController {
//
//    @Autowired
//    RestTemplate restTemplate;

//    @Autowired
//    DiscoveryClient discoveryClient;  //Eureka客户端发现

     @Autowired
    UserService userService;

    @RequestMapping("/consumer/{id}")
//    @HystrixCommand(fallbackMethod = "findByIdFallback")
    @HystrixCommand
    public String findByid(@PathVariable Long id){

//        String url="http://localhost:9092/user/findById?id="+id;
       /* //获取注册中心的所有实例列表。参数为：要获取的实例应用名称
        List<ServiceInstance> instances = discoveryClient.getInstances("provider-server");
        //获取实例列表的第一个实例对象
        ServiceInstance serviceInstance = instances.get(0);
        //获取实例的地址
        String host = serviceInstance.getHost();
        //获取实例的端口号
        int port = serviceInstance.getPort();
        String url="http://"+host+":"+port+"/user/findById?id="+id;*/
//       provider-server:请求的负载均衡的名称（访问提供者的名称）
//        String url="http://provider-server/user/findById?id="+id;
//        User User = restTemplate.getForObject(url, User.class);
        User user = userService.findById(id);
        return user.toString();
    }
    /**
     * @Author Mr.Dong
     * @Description 服务降级执行方法 参数和返回值要与被降级方法保持一致
     * @Date 17:54 2020/10/20
     * @Param [id]
     * @return java.lang.String
     **/
    public String findByIdFallback(Long id){
        return "很抱歉，服务器正忙！请您稍后再试！！！！！";
    }
    /**
     * @Author Mr.Dong
     * @Description //配置全局服务降级方法。  不能带参
     * @Date 18:04 2020/10/20
     * @Param []
     * @return java.lang.String
     **/
    public String defaultFallback(){
        return "【全局熔断策略】很抱歉，服务器正忙！请您稍后再试！！！！！";
    }

}
