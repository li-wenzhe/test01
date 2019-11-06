package com.itheima.dao.impl;

import com.itheima.damain.Category;
import com.itheima.damain.Route;
import com.itheima.damain.RouteImg;
import com.itheima.damain.Seller;
import com.itheima.dao.RouteDao;
import com.itheima.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());
    @Override
    public List<Route> hotRoutes() {
        String sql = "SELECT * FROM tab_route WHERE rflag = 1 ORDER BY COUNT DESC LIMIT 0,4";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Route.class));
    }

    @Override
    public List<Route> newRoutes() {
        String sql = "SELECT * FROM tab_route WHERE rflag = 1 ORDER BY rdate DESC LIMIT 0,4";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Route.class));
    }

    @Override
    public List<Route> themeRoutes() {
        String sql = "SELECT * FROM tab_route WHERE rflag = 1 AND isThemeTour = 1 LIMIT 0,4";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Route.class));
    }

    //查询对应页码的旅游路线
    @Override
    public List<Route> search(String rname, String cid, int pageSize, int index) {
        //String sql = "select * from tab_route where rflag=1 and rname like ? and cid = ? limit ?,?";
        String sql = "select * from tab_route where rflag=1";

        List<Object> params = new ArrayList<>();

        if (rname != null&&!"".equals(rname)) {
            sql += " and rname like ?";
            params.add("%"+rname+"%");
        }
        if (cid != null&&!"".equals(cid)) {
            sql += " and cid = ?";
            params.add(cid);
        }
        sql += " limit ?,?";
        params.add(index);
        params.add(pageSize);

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Route.class),params.toArray());//参数不能是集合,要转换成数组
    }

    //总共多少条数据
    @Override
    public int searchCount(String rname, String cid) {
        //String sql = "select count(*) from tab_route where rflag = 1 and rname like ? and cid = ?";
        String sql = "select count(*) from tab_route where rflag = 1";

        List<Object> params = new ArrayList<>();

        if (rname != null&&!"".equals(rname)) {
            sql += " and rname like ?";
            params.add("%"+rname+"%");
        }
        if (cid != null&&!"".equals(cid)) {
            sql += " and cid = ?";
            params.add(cid);
        }
        return jdbcTemplate.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public Route findByRid(String rid) {
        String sql = "select * from tab_route where rid=?";
        List<Route> routeList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class), rid);
        if (routeList != null&&routeList.size()>0) {
            return routeList.get(0);
        }
        return null;
    }

    @Override
    public Seller findSellerBySid(Integer sid) {
        String sql = "select * from tab_seller where sid=?";
        List<Seller> sellerList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
        if (sellerList != null&&sellerList.size()>0) {
            return sellerList.get(0);
        }
        return null;
    }

    @Override
    public Category findCategoryByCid(Integer cid) {
        String sql = "select * from tab_category where cid=?";
        List<Category> categoryList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class), cid);
        if (categoryList != null&&categoryList.size()>0) {
            return categoryList.get(0);
        }
        return null;
    }

    @Override
    public List<RouteImg> findRouteImg(String rid) {
        String sql = "select * from tab_route_img where rid=?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(RouteImg.class),rid);
    }
}
