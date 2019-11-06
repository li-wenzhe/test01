package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import com.itheima.jopo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;


public class AccountDaoImpl02 extends JdbcDaoSupport implements AccountDao {
//    @Autowired
//    private DriverManagerDataSource dataSource;
//    @PostConstruct
//    public void initDataSource(){
//        super.setDataSource(this.dataSource);
//    }

    @Override
    public void save(Account account) {
        super.getJdbcTemplate().update("INSERT INTO account(`name`,money) VALUES(?,?)", account.getName(), account.getMoney());

    }

    @Override
    public void update(Account account) {
        super.getJdbcTemplate().update("UPDATE account SET `name` = ?,money = ? WHERE id = ?", account.getName(), account.getMoney(), account.getId());

    }

    @Override
    public void delete(Integer id) {
        super.getJdbcTemplate().update("DELETE FROM account WHERE id = ?", id);

    }

    @Override
    public Account findById(Integer id) {
        return super.getJdbcTemplate().queryForObject("SELECT * FROM account WHERE id = ?", new BeanPropertyRowMapper<>(Account.class), id);
    }

    @Override
    public List<Account> findAll() {
        return super.getJdbcTemplate().query("SELECT * FROM account", new BeanPropertyRowMapper<>(Account.class));
    }

    @Override
    public Integer count() {
        return super.getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM account ", Integer.class);
    }
}
