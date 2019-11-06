package com.itheima.dao;

import com.itheima.pojo.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountDao {
    @Select("SELECT * FROM account")
    List<Account> findAll();

}
