package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.ItemsDao;
import com.itheima.ssm.pojo.Items;
import com.itheima.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsDao itemsDao;
    @Override
    public List<Items> list() {
        return itemsDao.findAll();
    }

    @Override
    public void save(Items items) {
        itemsDao.save(items);
    }
}
