package com.itheima.dao.impl;

import com.itheima.beans.Account;
import com.itheima.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

//继承方式的注解方法
@Repository("accountDao03")
public class AccountDaoImpl03 extends JdbcDaoSupport implements AccountDao {
    @Autowired
    private DriverManagerDataSource dataSource;
    @PostConstruct
    public void initDataSource(){
        super.setDataSource(this.dataSource);
    }


    @Override
    public void save() {

    }

    @Override
    public List<Account> queryAll() {
        return super.getJdbcTemplate().query("SELECT * FROM account",new BeanPropertyRowMapper<>(Account.class));
    }


}
