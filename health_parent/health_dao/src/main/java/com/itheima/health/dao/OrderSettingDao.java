package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.pojo.Setmeal;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderSettingDao {

    long findCountByOrderDate(Date orderDate);

    void editOrderSetting(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);

    List<OrderSetting> getOrderSettingByMoth(String date);

    OrderSetting findOrderSettingByOrderDate(Date date);

    void editReservationsByOrderDate(Date date);
}
