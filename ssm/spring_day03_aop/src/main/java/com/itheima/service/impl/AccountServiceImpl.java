package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.service.AccountService;
import org.aspectj.lang.ProceedingJoinPoint;
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
        return "666666666666666";
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
