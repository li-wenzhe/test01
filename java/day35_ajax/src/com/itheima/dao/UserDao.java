package com.itheima.dao;

import com.itheima.domain.User;
import com.itheima.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());
    public User findByName(String name) {
        String sql = "select * from user where name = ?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), name);
        if(userList!=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    public List<User> search(String name) {
        String sql = "select * from user where name like ?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%");

        return userList;
    }
}
