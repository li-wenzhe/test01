package com.itheima.dao;

import com.itheima.damain.Category;
import com.itheima.damain.Route;
import com.itheima.damain.RouteImg;
import com.itheima.damain.Seller;

import java.util.List;

public interface RouteDao {
    List<Route> hotRoutes();

    List<Route> newRoutes();

    List<Route> themeRoutes();

    List<Route> search(String rname, String cid, int pageSize, int index);

    int searchCount(String rname, String cid);

    Route findByRid(String rid);

    Seller findSellerBySid(Integer sid);

    Category findCategoryByCid(Integer cid);

    List<RouteImg> findRouteImg(String rid);
}
