package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 風楪fy
 * @date 2022-01-15 01:22
 **/
@Mapper
@Repository
public interface UserMapper {
    void insertAccount(@Param("username") String name, @Param("password") String pass, @Param("email") String mail, @Param("role") int role);
    void deleteAccountById(@Param("id") Integer id);
    void deleteAccountByMail(@Param("email") String email);
    void deleteAccountByName(@Param("username") String name);
    Account getAccountById(@Param("id") Integer id);
    Account getAccountByMail(@Param("email") String email);
    Account getAccountByName(@Param("name") String name);
    List<Account> getAccountList();
    Integer getCount();
}
