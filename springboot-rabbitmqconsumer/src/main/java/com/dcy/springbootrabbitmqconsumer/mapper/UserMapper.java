package com.dcy.springbootrabbitmqconsumer.mapper;

import com.dcy.springbootrabbitmqconsumer.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/24 15:47
 * @Version 1.0
 */
@Repository
@Mapper
public interface UserMapper {


    int addUser(User user);
}
