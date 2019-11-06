package com.itheima.ssm.dao;

import com.itheima.ssm.pojo.Items;

import java.util.List;

/**
 * @ClassName ItemsDao
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2019/10/17 16:16
 * @Version V1.0
 */
public interface ItemsDao {

    // 查询所有
    public List<Items> findAll();

    // 保存
    public void save(Items items);
}
