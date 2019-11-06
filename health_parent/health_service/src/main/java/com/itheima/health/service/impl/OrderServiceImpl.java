package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.MemberDao;
import com.itheima.health.dao.OrderDao;
import com.itheima.health.dao.OrderSettingDao;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Member;
import com.itheima.health.pojo.Order;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderService;
import com.itheima.health.service.OrderSettingService;
import com.itheima.health.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderSettingDao orderSettingDao;

    @Autowired
    MemberDao memberDao;

    @Override
    public Result order(Map map) throws Exception {
        //1.检查当前日期是否可以预约(是否进行了预约设置)
           //获取日期
        String orderDate = (String) map.get("orderDate");
           //把字符串转成日期格式
        Date date = DateUtils.parseString2Date(orderDate);
//        long countByOrderDate = orderSettingDao.findCountByOrderDate(date);后面要用到可预约人数,要得到OrderSetting对象
        OrderSetting orderSetting = orderSettingDao.findOrderSettingByOrderDate(date);
        if (orderSetting==null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

        //2.检查预约人数是否已满
        int number = orderSetting.getNumber();//可预约人数
        int reservations = orderSetting.getReservations();//已预约人数
        if (number<=reservations){
            //预约已满,不能预约
            return new Result(false,MessageConstant.ORDER_FULL);
        }

        //3.检查当前用户是否为会员,根据手机号判断
           //获取手机号
        String telephone = (String) map.get("telephone");
        Member member = memberDao.findMemberByTelephone(telephone);
        if (member!=null){//是会员,查询是否重复预约
            //封装查询条件(构造方法)
            Order orderParams = new Order(member.getId(),date,null,null, Integer.parseInt((String) map.get("setmealId")));
            //使用查新条件来查询对应的数据(通用的方法,根据条件查询,可以为空)
            List<Order> list = orderDao.findOrderListByCondition(orderParams);
            //是否重复预约
            if (list!=null&&list.size()>0){
                //重复预约
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }

        if (member==null){
            //当前用户不是会员,把用户信息添加到会员表中
            member = new Member();
            member.setName((String) map.get("name"));
            member.setSex((String) map.get("sex"));
            member.setPhoneNumber((String) map.get("telephone"));
            member.setIdCard((String) map.get("idCard"));
            member.setRegTime(new Date());
            memberDao.add(member);
        }

        //向预约表中添加数据
        //封装查询条件(构造方法)
        Order order = new Order(member.getId(),date,(String)map.get("orderType"),Order.ORDERSTATUS_NO,Integer.parseInt((String)map.get("setmealId")));
        orderDao.add(order);

        //根据预约时间,更新预约设置表,是的reservations字段+1
        orderSettingDao.editReservationsByOrderDate(date);

        return new Result(true,MessageConstant.ORDER_SUCCESS,order);
    }

    //根据id查询预约信息，包括体检人信息、套餐信息
    @Override
    public Map findById(Integer id) {
        //三表隐式内连接:order,member,setmeal
        Map map = orderDao.findById(id);
        if (map!=null){
            Date date = (Date)map.get("orderDate");//获取日期格式的时间
            //转成String格式
            try {
                String sDate = DateUtils.parseDate2String(date);
                //封装到map里
                map.put("orderDate",sDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    @Override
    public Integer findOrderCountByDate(String today) {
        return orderDao.findOrderCountByDate(today);
    }

    @Override
    public Integer findOrderVisitsByDate(String today) {
        return orderDao.findOrderVisitsByDate(today);
    }

    @Override
    public Integer findOrderCountBetweenDate(Map<String, Object> map) {
        return orderDao.findOrderCountBetweenDate(map);
    }

    @Override
    public Integer findOrderVisitsBetweenDate(Map<String, Object> map) {
        return orderDao.findOrderVisitsBetweenDate(map);
    }

    @Override
    public List<Map> findHotSetmeal() {
        return orderDao.findHotSetmeal();
    }
}
