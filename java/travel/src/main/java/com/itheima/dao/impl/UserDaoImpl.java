package com.itheima.dao.impl;

import com.itheima.damain.User;
import com.itheima.dao.UserDao;
import com.itheima.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public int add(User user) {
        String sql = "INSERT INTO tab_user (uid,username,PASSWORD,NAME,birthday,sex,telephone,email,STATUS,CODE) VALUE (?,?,?,?,?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(), user.getStatus(), user.getCode());
        return update;
    }

    @Override
    public int active(String code) {
        String sql = "update tab_user set status = 'Y' where code = ?";
        return jdbcTemplate.update(sql,code);
    }

    @Override
    public User login(String username, String password) {
        String sql = "select * from tab_user where username=? and password=?";
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        if (list != null&&list.size()>0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        String sql ="select * from tab_user where username = ?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username);
        if (userList != null&&userList.size()>0) {
            return userList.get(0);
        }
        return null;
    }
}
