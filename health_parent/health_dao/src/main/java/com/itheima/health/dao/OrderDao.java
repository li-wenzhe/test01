package com.itheima.health.dao;

import com.itheima.health.pojo.Order;
import com.itheima.health.pojo.OrderSetting;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderDao {

    List<Order> findOrderListByCondition(Order orderParams);

    void add(Order order);

    Map findById(Integer id);

    Integer findOrderCountByDate(String today);

    Integer findOrderVisitsByDate(String today);

    Integer findOrderCountBetweenDate(Map<String, Object> map);

    Integer findOrderVisitsBetweenDate(Map<String, Object> map);

    List<Map> findHotSetmeal();
}
