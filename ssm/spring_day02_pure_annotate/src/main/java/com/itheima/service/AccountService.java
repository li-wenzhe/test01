package com.itheima.service;

import com.itheima.bean.Account;

import java.util.List;

public interface AccountService {
    void save(Account account) throws Exception;

    void update(Account account) throws Exception;

    void delete(Integer id) throws Exception;

    Account findById(Integer id) throws Exception;

    List<Account> findAll() throws Exception;

}
