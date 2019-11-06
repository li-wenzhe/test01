package com.itheima.service.impl;

import com.itheima.damain.*;
import com.itheima.dao.RouteDao;
import com.itheima.dao.impl.RouteDaoImpl;
import com.itheima.service.RouteService;
import com.itheima.util.PageUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteServletImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    @Override
    public Map<String, List<Route>> usefulRoutes() {
        //人气旅游
        List<Route> hotRoutes = routeDao.hotRoutes();
        //最新旅游
        List<Route> newRoutes = routeDao.newRoutes();
        //主题旅游
        List<Route> themeRoutes = routeDao.themeRoutes();
        //把List集合放进map集合里
        Map<String,List<Route>> map = new HashMap<>();
        map.put("hotRoutes",hotRoutes);
        map.put("newRoutes",newRoutes);
        map.put("themeRoutes",themeRoutes);
        return map;
    }

    @Override
    public PageBean<Route> search(String rname, String cid, int pageNumber, int pageSize) {
        PageBean<Route> pageBean = new PageBean<>();
        //1.对应页码的旅游路线
        int index = (pageNumber-1)*pageSize;
        List<Route> routes = routeDao.search(rname,cid,pageSize,index);
        pageBean.setData(routes);

        //2.总数据数
        int totalCount = routeDao.searchCount(rname,cid);
        pageBean.setTotalCount(totalCount);

        //3.总页数
        int pageCount = (int) Math.ceil(totalCount * 1.0 / pageSize);
        pageBean.setPageCount(pageCount);

        //4.当前页码:pageNumber
        pageBean.setPageNumber(pageNumber);

        //5.分页条的起始页和结束页
        int[] pagination = PageUtils.pagination(pageNumber, pageCount);
        pageBean.setStart(pagination[0]);
        pageBean.setEnd(pagination[1]);
        return pageBean;
    }

    @Override
    public Route routeDetail(String rid) {
        //1.线路详情
        Route route = routeDao.findByRid(rid);

        //2.商家信息
        Seller seller = routeDao.findSellerBySid(route.getSid());
        route.setSeller(seller);

        //3.分类信息
        Category category = routeDao.findCategoryByCid(route.getCid());
        route.setCategory(category);

        //4.图片信息
        List<RouteImg> routeImgs = routeDao.findRouteImg(rid);
        route.setRouteImgList(routeImgs);

        return route;
    }
}
