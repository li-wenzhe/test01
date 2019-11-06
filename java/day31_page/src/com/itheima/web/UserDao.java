package com.itheima.web;

import com.itheima.domain.User;
import com.itheima.utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());
    public List<User> queryAll() {
        return jdbcTemplate.query("select * from tab_user",new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> pageQuery(int index, int pageSize) {

        return jdbcTemplate.query("select * from tab_user limit ?,?",new BeanPropertyRowMapper<>(User.class),index,pageSize);
    }

    public int count() {
        //总用户数
        return jdbcTemplate.queryForObject("select count(*) from tab_user",Integer.class);

    }
}
