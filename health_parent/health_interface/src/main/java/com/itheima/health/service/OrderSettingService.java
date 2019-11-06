package com.itheima.health.service;



import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    void add(List<OrderSetting> orderSettingsList);

    List<Map> getOrderSettingByMoth( String date);

    void editNumberByDate(OrderSetting orderSetting);
}
