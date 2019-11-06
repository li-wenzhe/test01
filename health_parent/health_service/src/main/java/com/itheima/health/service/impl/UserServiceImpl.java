package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.MemberDao;
import com.itheima.health.dao.UserDao;
import com.itheima.health.pojo.Member;
import com.itheima.health.pojo.User;
import com.itheima.health.service.MemberService;
import com.itheima.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;


    @Override
    public User findUserByUserName(String username) {
        return userDao.findUserByUserName(username);
    }
}
