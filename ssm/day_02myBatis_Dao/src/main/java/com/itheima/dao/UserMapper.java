package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

public interface UserMapper {
    //1.根据用户id查询一个用户信息
    public User findById(Integer id);
    //2.根据用户名称模糊查询用户信息列表
    public List<User> findByUserName(String username);
    //3.添加用户信息
    public int addUser(User user);
}
