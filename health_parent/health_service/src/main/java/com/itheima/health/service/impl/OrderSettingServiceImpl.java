package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.OrderSettingDao;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    OrderSettingDao orderSettingDao;


    @Override
    public void add(List<OrderSetting> orderSettingsList) {
            if (orderSettingsList!=null&&orderSettingsList.size()>0){
                for (OrderSetting orderSetting : orderSettingsList) {
                    //检查此数据(日期)是否存在
                    long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                    if (count>0){
                        //存在,执行更新操作
                        orderSettingDao.editOrderSetting(orderSetting);
                    }else{
                        //不存在则执行添加操作
                        orderSettingDao.add(orderSetting);
                    }
                }
            }


    }

    @Override
    public List<Map> getOrderSettingByMoth(String date) {
        List<OrderSetting> orderSettingList = orderSettingDao.getOrderSettingByMoth(date);
        List<Map> list = new ArrayList<Map>();
        if (orderSettingList!=null&&orderSettingList.size()>0){
            for (OrderSetting orderSetting : orderSettingList) {
                Map map = new HashMap();
                map.put("date",orderSetting.getOrderDate().getDate());//获得具体日期,几号
                map.put("number",orderSetting.getNumber());
                map.put("reservations",orderSetting.getReservations());
                list.add(map);
            }
            return list;
        }
        return null;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        //检查此数据(日期)是否存在
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count>0){
            //存在,执行更新操作
            orderSettingDao.editOrderSetting(orderSetting);
        }else{
            //不存在则执行添加操作
            orderSettingDao.add(orderSetting);
        }
    }
}
