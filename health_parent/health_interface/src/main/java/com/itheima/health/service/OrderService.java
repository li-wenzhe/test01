package com.itheima.health.service;



import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Result order(Map map) throws Exception;

    Map findById(Integer id);

    Integer findOrderCountByDate(String today);

    Integer findOrderVisitsByDate(String today);

    Integer findOrderCountBetweenDate(Map<String, Object> weekMap);

    Integer findOrderVisitsBetweenDate(Map<String, Object> weekMap);

    List<Map> findHotSetmeal();
}
