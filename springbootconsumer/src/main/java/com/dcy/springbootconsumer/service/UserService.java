package com.dcy.springbootconsumer.service;

import com.dcy.springbootconsumer.domain.User;
import com.dcy.springbootconsumer.log.MyConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName UserService
 * @Description Feign远程调用接口
 * @Author Mr.Dong
 * @Date 2020/10/20 19:48
 * @Version 1.0
 */
@FeignClient(value = "provider-server",
        //熔断器类
        fallback =UserServiceImpl.class,
//        日志配置类
        configuration = MyConfiguration.class
)
public interface UserService {

    @RequestMapping("/user/findById")
    User findById(@RequestParam("id") Long id);

}
