package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.ReportDao;
import com.itheima.health.dao.RoleDao;
import com.itheima.health.service.MemberService;
import com.itheima.health.service.OrderService;
import com.itheima.health.service.ReportService;
import com.itheima.health.service.RoleService;
import com.itheima.health.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportDao reportDao;

    @Autowired
    MemberService memberService;

    @Autowired
    OrderService orderService;


    @Override
    public Map<String, Object> getBusinessReport() throws Exception {
        //获得当前日期
        String today = DateUtils.parseDate2String(DateUtils.getToday());
        //获得当前日期新增的会员
        Integer todayNewMember = reportDao.findTodayNewMember(today);

        //获得总会员数
        Integer totalMember = memberService.findMemberCount();

        //获得本周周一
        String monday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
        //本周新增会员数
        Integer thisWeekNewMember = memberService.findMemberCountAfterDate(monday);

        //获得本月一号
        String firstDay4ThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
        //本月新增会员数
        Integer thisMonthNewMember = memberService.findMemberCountAfterDate(firstDay4ThisMonth);

        //今日预约数
        Integer todayOrderNumber = orderService.findOrderCountByDate(today);

        //今日到诊数
        Integer todayVisitsNumber = orderService.findOrderVisitsByDate(today);

        //获得本周周日
        String sunday = DateUtils.parseDate2String(DateUtils.getSundayOfThisWeek());
        //封装
        Map<String,Object> weekMap = new HashMap<>();
        weekMap.put("begin",monday);
        weekMap.put("end",sunday);
        //本周预约数
        Integer thisWeekOrderNumber = orderService.findOrderCountBetweenDate(weekMap);

        //本周到诊数
        Integer thisWeekVisitsNumber = orderService.findOrderVisitsBetweenDate(weekMap);

        //获得本月最后一天
        String lastDay4ThisMonth = DateUtils.parseDate2String(DateUtils.getLastDay4ThisMonth());
        //封装
        Map<String,Object> monthMap = new HashMap<>();
        monthMap.put("begin",firstDay4ThisMonth);
        monthMap.put("end",lastDay4ThisMonth);
        //本月预约人数
        Integer thisMonthOrderNumber = orderService.findOrderCountBetweenDate(monthMap);

        //本月到诊数
        Integer thisMonthVisitsNumber = orderService.findOrderVisitsBetweenDate(monthMap);

        //热门套餐
        List<Map> hotSetmeal = orderService.findHotSetmeal();
        Map<String,Object> result = new HashMap<>();
        result.put("reportDate",today);
        result.put("todayNewMember",todayNewMember );
        result.put("totalMember",totalMember );
        result.put("thisWeekNewMember",thisWeekNewMember );
        result.put("thisMonthNewMember",thisMonthNewMember );
        result.put("todayOrderNumber",todayOrderNumber );
        result.put("todayVisitsNumber",todayVisitsNumber );
        result.put("thisWeekOrderNumber",thisWeekOrderNumber );
        result.put("thisWeekVisitsNumber",thisWeekVisitsNumber );
        result.put("thisMonthOrderNumber",thisMonthOrderNumber );
        result.put("thisMonthVisitsNumber",thisMonthVisitsNumber );
        result.put("hotSetmeal",hotSetmeal );
        return result;
    }
}
