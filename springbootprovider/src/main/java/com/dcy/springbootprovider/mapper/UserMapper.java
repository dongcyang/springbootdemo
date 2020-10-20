package com.dcy.springbootprovider.mapper;

import com.dcy.springbootprovider.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/20 12:38
 * @Version 1.0
 */
@Mapper
@Repository
public interface UserMapper {

 /**
  * @Author Mr.Dong
  * @Description //根据用户id查询用户信息
  * @Date 12:39 2020/10/20
  * @Param [id]
  * @return com.dcy.springbootprovider.domain.User
  **/
    User findById(Long id);

}
