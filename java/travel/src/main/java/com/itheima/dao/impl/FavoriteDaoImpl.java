package com.itheima.dao.impl;

import com.itheima.damain.Favorite;
import com.itheima.damain.QueryOV;
import com.itheima.damain.Route;
import com.itheima.damain.User;
import com.itheima.dao.FavoriteDao;
import com.itheima.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate jdbc = new JdbcTemplate(JdbcUtils.getDataSource());
    @Override
    public Favorite findFavorite(String rid, User user) {
        String sql = "select * from tab_favorite where rid=? and uid=?";
        List<Favorite> favoriteList = jdbc.query(sql, new BeanPropertyRowMapper<>(Favorite.class), rid, user.getUid());
        if (favoriteList != null&&favoriteList.size()>0) {
            return favoriteList.get(0);
        }
        return null;
    }

    @Override
    public void saveFavorite(Favorite favorite, JdbcTemplate jdbcTemplate) {
        String sql = "insert into tab_favorite (rid,date,uid) values (?,?,?)";
        jdbcTemplate.update(sql,favorite.getRid(),favorite.getDate(),favorite.getUid());
    }

    //添加收藏
    @Override
    public void updateFavorite(String rid, JdbcTemplate jdbcTemplate) {
        String sql ="update tab_route set count = count +1 where rid = ?";
        jdbcTemplate.update(sql,rid);
    }

    //我的收藏数量
    @Override
    public Integer countFavoriteByUid(User user) {
        String sql ="select count(*) from tab_favorite where uid = ?";
        return jdbc.queryForObject(sql,Integer.class,user.getUid());
    }

    //我的收藏数据
    @Override
    public List<Route> myFavorite(User user, int index, Integer pageSize) {
        String sql = "SELECT r.* FROM tab_favorite f LEFT JOIN tab_route r ON f.rid = r.rid WHERE uid=? limit ?,?";
        List<Route> routeList = jdbc.query(sql, new BeanPropertyRowMapper<>(Route.class),user.getUid(),index,pageSize);
        return routeList;
    }

    //收藏排行榜的总数据量
    @Override
    public int countFavoriteByOV(QueryOV ov) {
        /*String sql = "select count(*) from tab_route where rflag = 1 and rname like ? and price <= ? and price >=?";*/
        String sql = "select count(*) from tab_route where rflag = 1 ";
        List<Object> list = new ArrayList<>();
        if(ov.getRname()!=null&&!"".equals(ov.getRname())){
            sql += " and rname LIKE ? ";
            list.add("%"+ov.getRname()+"%");
        }
        if (ov.getMaxprice()!=null&&!"".equals(ov.getMaxprice())){
            sql +=" AND price <= ? ";
            list.add(ov.getMaxprice());
        }
        if (ov.getMinprice()!=null&&!"".equals(ov.getMinprice())){
            sql += " AND price >= ? ";
            list.add(ov.getMinprice());
        }
        return jdbc.queryForObject(sql,Integer.class,list.toArray());
    }

    //收藏排行榜分页数据
    @Override
    public List<Route> rankRoute(QueryOV ov, int index, int pageSize) {
        /*String sql = "select * from tab_route where rflag = 1 and rname like ? and price <= ? and price >= ? order by count desc limit ? ,?";*/
        String sql = "select * from tab_route where rflag = 1 ";
        List<Object> list = new ArrayList<>();
        if(ov.getRname()!=null&&!"".equals(ov.getRname())){
            sql += " and rname LIKE ? ";
            list.add("%"+ov.getRname()+"%");
        }
        if (ov.getMaxprice()!=null&&!"".equals(ov.getMaxprice())){
            sql +=" AND price <= ? ";
            list.add(ov.getMaxprice());
        }
        if (ov.getMinprice()!=null&&!"".equals(ov.getMinprice())){
            sql += " AND price >= ? ";
            list.add(ov.getMinprice());
        }
        sql +=" order by count desc limit ? ,?";
        list.add(index);
        list.add(pageSize);
        return jdbc.query(sql,new BeanPropertyRowMapper<>(Route.class),list.toArray());
    }
}
