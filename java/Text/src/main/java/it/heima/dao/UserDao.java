package it.heima.dao;

import it.heima.domain.User;

import java.util.List;

public interface UserDao {
    List<User> login(String username, String password);
}
