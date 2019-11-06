package com.itheima.service;

import com.itheima.damain.User;

public interface UserService {
    boolean register(User user) throws Exception;

    boolean active(String code);

    User login(String username, String password) throws Exception;

    boolean isExist(String username);
}
