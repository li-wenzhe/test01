package com.itheima.health.service;



import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.CheckItem;

public interface CheckitemService {
    void add(CheckItem checkItem);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void deleteById(Integer id);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);
}
