package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import com.itheima.jopo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Account account) {
        jdbcTemplate.update("INSERT INTO account(`name`,money) VALUES(?,?)", account.getName(), account.getMoney());

    }

    @Override
    public void update(Account account) {
        jdbcTemplate.update("UPDATE account SET `name` = ?,money = ? WHERE id = ?", account.getName(), account.getMoney(), account.getId());

    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM account WHERE id = ?", id);

    }

    @Override
    public Account findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM account WHERE id = ?", new BeanPropertyRowMapper<>(Account.class), id);
    }

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("SELECT * FROM account", new BeanPropertyRowMapper<>(Account.class));
    }

    @Override
    public Integer count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM account ", Integer.class);
    }
}
