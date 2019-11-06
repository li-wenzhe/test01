package com.itheima.service;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;

import java.util.List;

public class UserService {
    private UserDao userDao =new UserDao();
    public boolean isExist(String name) {
        //调用dao,根据name查询用户
        User user = userDao.findByName(name);

        return user!=null;
    }

    public List<User> search(String name) {
        List<User> user = userDao.search(name);
        return user;
    }
}
