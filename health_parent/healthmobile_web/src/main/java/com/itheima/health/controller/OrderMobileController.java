package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.constant.RedisMessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Order;
import com.itheima.health.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderMobileController {
    @Reference
    OrderService orderService;

    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map){
        //一:检验验证码
        //1.获取手机号码
        String telephone = (String) map.get("telephone");
        //2.获取redis里的验证码
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        //3.获取用户输入的验证码
        String validateCode = (String) map.get("validateCode");
        //4.校验验证码
        if (validateCode==null||!validateCode.equals(codeInRedis)){
            return new Result(false,MessageConstant.VALIDATECODE_ERROR);
        }
        //检验成功才执行下面的代码
        //调用体检预约服务
        Result result = null;
        try {
            map.put("orderType", Order.ORDERTYPE_WEIXIN);//添加orderType为微信预约
            result=orderService.order(map);
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        return result;
    }

    //根据id查询预约信息,包括套餐信息和会员信息:体检人,体检套餐,体检日期,预约类型
    //三表隐式内连接:order,member,setmeal
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Map map = null;
        try {
            map = orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
