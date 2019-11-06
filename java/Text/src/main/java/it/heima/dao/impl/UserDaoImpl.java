package it.heima.dao.impl;

import it.heima.dao.UserDao;
import it.heima.domain.User;
import it.heima.util.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());

    @Override
    public List<User> login(String username, String password) {
        String sql = "select * from tab_user where username = ? and password = ?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        return users;
    }
}
