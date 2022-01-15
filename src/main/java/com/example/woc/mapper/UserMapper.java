package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Mapper
@Repository
public interface UserMapper {
    void insertAccount(@Param("username") String name, @Param("password") String pass, @Param("email") String mail);
    void deleteAccount(@Param("username") String name);
    String getPasswordByMail(@Param("email") String email);
    String getPasswordByName(@Param("username") String name);
    Integer getCount();
}
