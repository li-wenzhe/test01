package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.util.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());
    @Override
    public User queryUser(User user) {
        String sql = "select * from user where username = ? and password = ?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername(),user.getPassword());
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
