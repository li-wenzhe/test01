package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.dao.CheckitemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckGroupService;
import com.itheima.health.service.CheckitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    CheckGroupDao checkGroupDao;


    @Override
    public List<CheckItem> findAll() {
        return checkGroupDao.findAll();
    }

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //1.把检查组添加进数据库,返回id
        checkGroupDao.add(checkGroup);
        //2.遍历数组,把返回的id和遍历的id插入数据库中
        if (checkitemIds!=null&&checkitemIds.length>0){
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckGroupAndCheckItem(checkGroup.getId(),checkitemId);
            }
        }
    }

    @Override
    public List<CheckGroup> findAllCheckGroup() {
        return checkGroupDao.findAllCheckGroup();
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //1.删除中间表的关联
        checkGroupDao.deleteCheckGroupItem(checkGroup.getId());
        //2.保存检查组
        checkGroupDao.edit(checkGroup);
        //3.保存中间表数据
        if (checkitemIds!=null&&checkitemIds.length>0){
            for (Integer checkitemId : checkitemIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("checkitem_id",checkitemId);
                map.put("checkgroup_id",checkGroup.getId());
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }

    }
}
