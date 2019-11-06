package com.itheima.dao;

import com.itheima.damain.User;

public interface UserDao {
    int add(User user);

    int active(String code);

    User login(String username, String password);

    User findByUsername(String username);
}
