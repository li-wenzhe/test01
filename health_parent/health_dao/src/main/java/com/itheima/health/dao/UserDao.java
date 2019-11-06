package com.itheima.health.dao;

import com.itheima.health.pojo.Order;
import com.itheima.health.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    User findUserByUserName(String username);
}
