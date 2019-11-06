package com.itheima.web;

import com.itheima.domain.User;
import com.itheima.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());
    public List<User> queryAll() {
        //执行sql语句

        return jdbcTemplate.query("select * from tab_user",new BeanPropertyRowMapper<>(User.class));

    }

    public int add(User user) {
        String sql = "insert into tab_user(id,name,sex,age,address,qq,email) values (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,user.getId(),user.getName(),user.getSex(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    public User findById(String id) {
        String sql = "select * from tab_user where id=?";
        List<User> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id);
        if (query!=null&&query.size()>0){
            return query.get(0);
        }
        return null;
    }

    public int edit(User user) {
        String sql = "UPDATE tab_user SET NAME = ?,sex=?,age=?,address=?,qq=?,email=? WHERE id = ?";
        int update = jdbcTemplate.update(sql, user.getName(), user.getSex(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
        return update;
    }

    public int delete(String id) {
        String sql = "DELETE FROM tab_user WHERE id = ?";
        int update = jdbcTemplate.update(sql, id);
        return update;
    }
}
