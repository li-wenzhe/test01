package com.itheima.dao.impl;

import com.itheima.beans.Account;
import com.itheima.dao.AccountDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;


//继承方式的xml文件配置方法
public class AccountDaoImpl02 extends JdbcDaoSupport implements AccountDao {


    @Override
    public void save() {

    }

    @Override
    public List<Account> queryAll() {
        return super.getJdbcTemplate().query("SELECT * FROM account",new BeanPropertyRowMapper<>(Account.class));
    }


}
