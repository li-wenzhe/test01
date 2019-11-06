package com.itheima.service;

import com.itheima.damain.*;

import java.sql.SQLException;

public interface FavoriteService {
    Boolean isFavorite(String rid, User user);

    boolean addFavorite(Favorite favorite) throws SQLException;

    PageBean<Route> myFavorite(User user, Integer pageNumber, Integer pageSize);

    PageBean<Route> favoriteRank(QueryOV ov, int pageNumber, int pageSize);
}
