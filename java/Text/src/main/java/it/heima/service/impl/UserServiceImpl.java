package it.heima.service.impl;

import it.heima.dao.UserDao;
import it.heima.dao.impl.UserDaoImpl;
import it.heima.domain.User;
import it.heima.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean login(String username, String password) {
        List<User> userList = userDao.login(username,password);
        return userList!=null;
    }
}
