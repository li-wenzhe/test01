package com.itheima.service.impl;

import com.itheima.damain.*;
import com.itheima.dao.FavoriteDao;
import com.itheima.dao.impl.FavoriteDaoImpl;
import com.itheima.service.FavoriteService;
import com.itheima.util.JdbcUtils;
import com.itheima.util.PageUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public Boolean isFavorite(String rid, User user) {
        Favorite favorite = favoriteDao.findFavorite(rid,user);
        return favorite!=null;
    }

    @Override
    public boolean addFavorite(Favorite favorite) throws SQLException {

        //1.获取连接池对象
        DataSource dataSource =JdbcUtils.getDataSource();
        //2.创建JdbcTemplate对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //3.初始化线程池标志:要使用事务
        TransactionSynchronizationManager.initSynchronization();
        //4.从连接池里获取一个连接
        Connection connection = DataSourceUtils.getConnection(dataSource);


        try {
            //5.开启事务
            connection.setAutoCommit(false);
            //6.插入收藏数据
            favoriteDao.saveFavorite(favorite,jdbcTemplate);
            //7.更新收藏数量
            favoriteDao.updateFavorite(favorite.getRid(),jdbcTemplate);
            //8.提交事务
            connection.commit();

            return true;
        } catch (Exception e) {
            //9.出现异常回滚事务
            connection.rollback();
            e.printStackTrace();
        }finally {
            //10.把connection恢复成默认的
            connection.setAutoCommit(true);
            //11.清理线程标志
            TransactionSynchronizationManager.clearSynchronization();
        }

        return false;
    }

    @Override
    public PageBean<Route> myFavorite(User user, Integer pageNumber, Integer pageSize) {
        PageBean<Route> pageBean =new PageBean<>();
        /*当前页码 */
        pageBean.setPageNumber(pageNumber);
        /*每页多少条*/
        pageBean.setPageSize(pageSize);
        /*总共多少数据*/
        Integer totalCount = favoriteDao.countFavoriteByUid(user);
        pageBean.setTotalCount(totalCount);
        /*分了多少页*/
        Integer pageCount = PageUtils.calcPageCount(totalCount,pageSize);
        pageBean.setPageCount(pageCount);
        /*页码条从几开始显示*/
        int[] startEnd = PageUtils.pagination(pageNumber,pageCount);
        pageBean.setStart(startEnd[0]);
        /*页码条显示到几结束*/
        pageBean.setEnd(startEnd[1]);
        /*当前页的数据集合*/
        int index = PageUtils.calcSqlLimitIndex(pageNumber,pageSize);
        List<Route> routesList = favoriteDao.myFavorite(user,index,pageSize);
        pageBean.setData(routesList);
        return pageBean;
    }

    @Override
    public PageBean<Route> favoriteRank(QueryOV ov, int pageNumber, int pageSize) {
        PageBean<Route> pageBean = new PageBean<>();
        /*当前页码 */
        pageBean.setPageNumber(pageNumber);
        /*每页多少条*/
        pageBean.setPageSize(pageSize);
        /*总共多少数据*/
        int totalCount = favoriteDao.countFavoriteByOV(ov);
        pageBean.setTotalCount(totalCount);
        /*分了多少页*/
        int pageCount = PageUtils.calcPageCount(totalCount, pageSize);
        pageBean.setPageCount(pageCount);
        /*页码条从几开始显示*/
        int[] pagination = PageUtils.pagination(pageNumber, pageCount);
        pageBean.setStart(pagination[0]);
        /*页码条显示到几结束*/
        pageBean.setEnd(pagination[1]);
        /*当前页的数据集合*/
        int index = PageUtils.calcSqlLimitIndex(pageNumber,pageSize);
        List<Route> routeList = favoriteDao.rankRoute(ov,index,pageSize);
        pageBean.setData(routeList);

        return pageBean;
    }
}
