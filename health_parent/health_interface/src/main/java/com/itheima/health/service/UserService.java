package com.itheima.health.service;



import com.itheima.health.pojo.Member;
import com.itheima.health.pojo.User;

public interface UserService {

    User findUserByUserName(String username);
}
