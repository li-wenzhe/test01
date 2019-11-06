package com.itheima.health.service;



import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

public interface CheckGroupService {

    List<CheckItem> findAll();

    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    List<CheckGroup> findAllCheckGroup();

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup, Integer[] checkitemIds);
}
