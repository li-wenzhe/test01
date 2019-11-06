package com.itheima.dao.impl;

import com.itheima.beans.Account;
import com.itheima.dao.AccountDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AccountDaoImpl implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save() {

    }

    @Override
    public List<Account> queryAll() {
        return jdbcTemplate.query("SELECT * FROM account",new BeanPropertyRowMapper<>(Account.class));
    }


}
