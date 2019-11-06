package com.itheima.web;

import com.itheima.domain.User;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();
    public List<User> queryAll() {
        //查询得到所有用户,调用dao
        return userDao.queryAll();
    }
    //添加用户
    public boolean add(User user) {
        int count =userDao.add(user);
        return count>0;
        /*if(true){
         true}*/
    }

    public User findById(String id) {
        User user = userDao.findById(id);
        return user;
    }

    public boolean edit(User user) {
        int count = userDao.edit(user);
        return count>0;
    }

    public boolean delete(String id) {
        int count = userDao.delete(id);
        return count>0;
    }
}
