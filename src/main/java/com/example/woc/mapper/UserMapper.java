package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 風楪fy
 * @date 2022-01-15 01:22
 **/
@Mapper
@Repository
public interface UserMapper {
    void insertAccount(@Param("username") String name, @Param("password") String pass, @Param("email") String mail, @Param("role") int role);
    void deleteAccountByMail(@Param("email") String email);
    void deleteAccountByName(@Param("username") String name);
    Account getAccountByMail(@Param("email") String email);
    Account getAccountByName(@Param("name") String name);
    Integer getCount();
}
