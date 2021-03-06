package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckitemDao {

    void add(CheckItem checkItem);

    Page<CheckItem> findPage(String queryString);

    void deleteById(Integer id);

    Integer findCheckGroupAndCheckItemCountByCheckItemId(Integer id);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);

    List<CheckItem> findCheckItemListById(Integer id);
}
