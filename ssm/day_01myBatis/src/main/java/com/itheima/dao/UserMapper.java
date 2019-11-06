package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

public interface UserMapper {
    public List<User> findAll();
    public int addUser(User user);
    public int deleteUserById(int id);
    public List<User> findUserByUserName(String username);
    public List<User> findUserByAddress(String address);
}
