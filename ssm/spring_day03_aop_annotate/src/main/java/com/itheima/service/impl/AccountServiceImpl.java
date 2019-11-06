package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountDao accountDao;

    @Override
    public void save() {
        accountDao.save();
    }

    @Override
    public String delete() {
        accountDao.delete();
        return "delete----->ok";
    }

    @Override
    public void queryAll() {
        accountDao.queryAll();
    }

    @Override
    public void queryById() {
        accountDao.queryById();
    }

    @Override
    public void addAccount() {
        accountDao.addAccount();
    }
}
