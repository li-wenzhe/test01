package com.itheima.dao;

import com.itheima.bean.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao {

    void save(Account account) throws SQLException;

    void update(Account account) throws SQLException;

    void delete(Integer id) throws SQLException;

    Account findById(Integer id) throws SQLException;

    List<Account> findAll() throws SQLException;

}
