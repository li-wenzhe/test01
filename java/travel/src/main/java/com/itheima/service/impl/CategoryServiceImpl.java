package com.itheima.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.damain.Category;
import com.itheima.dao.CategoryDao;
import com.itheima.dao.impl.CategoryDaoImpl;
import com.itheima.service.CategoryService;
import com.itheima.util.JedisUtils;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public String queryAll() throws JsonProcessingException {
        //从缓存里查询,得到的是String类型
        String categories = JedisUtils.getCache("categories");

        //如果缓存里没有,就从mysql里查询,然后把数据转换为json类型的的字符串存到缓存里,再返回数据
        if (categories == null||"".equals(categories)) {
            List<Category> categoryList = categoryDao.queryAll();
            //转换为json类型的字符串
            ObjectMapper objectMapper = new ObjectMapper();
            categories= objectMapper.writeValueAsString(categoryList);
            //把数据存到缓存里
            JedisUtils.setCache("categories",categories);
        }
        return categories;
    }
}
