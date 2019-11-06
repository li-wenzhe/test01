package com.itheima.web;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();
    public List<User> queryAll() {

        return userDao.queryAll();
    }

    public PageBean<User> pageQuery(int pageNumber, int pageSize) {
        //准备结果页面上的数据:需要什么数据就提供什么数据,需要几项数据就提供几项数据
        PageBean<User> pageBean=new PageBean<>();

        //1.pageNumber对应的用户列表
        int index = (pageNumber-1)*pageSize;//开始索引
        List<User> userList = userDao.pageQuery(index,pageSize);
        pageBean.setDataList(userList);

        //2.总页数
        int count = userDao.count();
        int pegeCount =(int) Math.ceil(count*1.0/ pageSize);//总用户数/每页个数=总页数
        pageBean.setPageCount(pegeCount);

        //3.当前页码:就是参数pageNumber
        pageBean.setPageNumber(pageNumber);

        //4.返回结果数据:方法返回值只能有一个对象,而需要返回三项数据
            //把三项数据放到map里面.根据key获取
            //把三项数据封装到JavaBean里.dataList:用于放数据的列表   pageCount:用于放总页数    pageNum:用于放当前页码
        return pageBean;
    }
}
