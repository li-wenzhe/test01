package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CheckGroupDao {


    List<CheckItem> findAll();

    void add(CheckGroup checkGroup);

    void addCheckGroupAndCheckItem(@Param(value = "checkGroupId") Integer checkGroupId, @Param(value = "checkitemId") Integer checkitemId);

    List<CheckGroup> findAllCheckGroup();

    Page<CheckGroup> findPage(String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void deleteCheckGroupItem(Integer id);

    void edit(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    List<CheckGroup> findCheckGroupListById(Integer id);
}
