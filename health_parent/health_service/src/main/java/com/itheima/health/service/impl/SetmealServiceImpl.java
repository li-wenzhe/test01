package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.CheckitemDao;
import com.itheima.health.dao.SetmealDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.CheckitemService;
import com.itheima.health.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    SetmealDao setmealDao;

    @Autowired
    JedisPool jedisPool;

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //1.添加setmeal表,返回id
        setmealDao.addSetmeal(setmeal);
        //2.添加中间表数据
        if (checkgroupIds!=null&&checkgroupIds.length>0){
            //绑定套餐与检查组的多对多关系
            setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
        }

        //3.向redis中集合的key值为setmealPicDbResources下保存数据(文件名),数据为可变参数
        jedisPool.getResource().sadd("setmealPicDbResources",setmeal.getImg());
    }

    //分页查询
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    //查询所有套餐
    @Override
    public List<Setmeal> findAll() {

        return setmealDao.findAll();
    }

    //根据id查询套餐
    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }

    @Override
    public List<Map<String, Object>> findSetmealOrderCount() {
        return setmealDao.findSetmealOrderCount();
    }

    //绑定套餐与检查组的多对多关系
    public void setSetmealAndCheckGroup(Integer setmealId,Integer[] checkgroupIds){
        //遍历checkgroupIds
        for (Integer checkgroupId : checkgroupIds) {
            Map<String,Integer> map = new HashMap<>();
            map.put("setmeal_id",setmealId);
            map.put("checkgroup_id",checkgroupId);
            setmealDao.setSetmealAndCheckGroup(map);
        }

    }
}
