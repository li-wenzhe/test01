package com.itheima.dao;

import com.itheima.damain.Favorite;
import com.itheima.damain.QueryOV;
import com.itheima.damain.Route;
import com.itheima.damain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface FavoriteDao {
    Favorite findFavorite(String rid, User user);

    void saveFavorite(Favorite favorite, JdbcTemplate jdbcTemplate);

    void updateFavorite(String rid, JdbcTemplate jdbcTemplate);

    Integer countFavoriteByUid(User user);

    List<Route> myFavorite(User user, int index, Integer pageSize);

    int countFavoriteByOV(QueryOV ov);


    List<Route> rankRoute(QueryOV ov, int index, int pageSize);
}
