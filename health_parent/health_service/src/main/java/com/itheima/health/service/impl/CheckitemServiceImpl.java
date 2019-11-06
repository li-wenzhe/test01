package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.CheckitemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = CheckitemService.class)
@Transactional
public class CheckitemServiceImpl implements CheckitemService {
    @Autowired
    CheckitemDao checkitemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkitemDao.add(checkItem);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkitemDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        //使用检查项id,查询检查项
        Integer count = checkitemDao.findCheckGroupAndCheckItemCountByCheckItemId(id);
        //存在数据
        if(count >0){
            throw new RuntimeException("当前检查项与检查组之间存在关联关系,不能删除");
        }
        checkitemDao.deleteById(id);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkitemDao.findById(id);
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkitemDao.edit(checkItem);
    }
}
