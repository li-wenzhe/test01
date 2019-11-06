package com.itheima.dao;

import com.itheima.beans.Account;

import java.util.List;

public interface AccountDao {
    void save();
    List<Account>  queryAll();
}
