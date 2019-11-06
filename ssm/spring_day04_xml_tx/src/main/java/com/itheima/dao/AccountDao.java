package com.itheima.dao;

import com.itheima.jopo.Account;

import java.util.List;

public interface AccountDao {
    void save(Account account);

    void update(Account account);

    void delete(Integer id);

    Account findById(Integer id);

    List<Account> findAll();

    Integer count();
}
