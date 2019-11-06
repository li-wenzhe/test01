package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;

public class AccountDaoImpl implements AccountDao {


    @Override
    public void save() {
        System.out.println("没增强的save");
    }

    @Override
    public void delete() {
        System.out.println("没增强的delete");
    }

    @Override
    public void queryAll() {
        System.out.println("没增强的queryAll");
        int i = 1/0;
    }

    @Override
    public void queryById() {
        int i = 1/0;
        System.out.println("没增强的queryById");
    }

    @Override
    public void addAccount() {
        System.out.println("没增强的addAccount");
    }
}
