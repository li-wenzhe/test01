package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.OrderSettingService;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.POIUtils;
import com.itheima.health.utils.QiniuUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.*;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    OrderSettingService orderSettingService;

    //Excel文件上传，并解析文件内容保存到数据库
    @RequestMapping("/upload")
    public Result upload (MultipartFile excelFile){
        try {
            //读取Excel文件数据
            List<String[]> list = POIUtils.readExcel(excelFile);//把每行中的值作为一个数组，所有行作为一个集合返回
            if (list!=null&&list.size()>0){
                List<OrderSetting> orderSettingsList = new ArrayList<OrderSetting>();
                for (String[] strings : list) {//list是每一行的数组
                    //new Date():将字符串转换成日期;Integer.parseInt(),将Integer转换成int类型
                    OrderSetting orderSetting = new OrderSetting(new Date(strings[0]),Integer.parseInt(strings[1]));
                    orderSettingsList.add(orderSetting);//将OrderSetting对象存进数组里
                }
                orderSettingService.add(orderSettingsList);
            }
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMoth")
    public Result getOrderSettingByMoth(String date){//参数格式为2019-10
        try {
            List<Map> list = orderSettingService.getOrderSettingByMoth(date);
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }

}
