package com.itheima.service.impl;

import com.itheima.damain.User;
import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.service.UserService;
import com.itheima.util.Md5Utils;

public class  UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    //注册功能,调用dao,把数据输入数据库
    @Override
    public boolean register(User user) throws Exception {
        //对用户密码进行加密
        String md5 = Md5Utils.encodeByMd5(user.getPassword());
        user.setPassword(md5);//把密码改为加密后的密码
        int add = userDao.add(user);
        return add>0;
    }

    //激活功能
    @Override
    public boolean active(String code) {
        //调用dao
        int count = userDao.active(code);//根据code把激活状态改为Y,返回查到的条数
        return count>0;
    }

    @Override
    public User login(String username, String password) throws Exception {
        //密码加密
        password = Md5Utils.encodeByMd5(password);
        //调用dao
        User user = userDao.login(username,password);
        return user;
    }

    @Override
    public boolean isExist(String username) {
        User user = userDao.findByUsername(username);
        return user!=null;
    }
}
