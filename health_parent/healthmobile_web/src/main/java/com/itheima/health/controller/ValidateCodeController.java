package com.itheima.health.controller;

import com.itheima.health.constant.MessageConstant;
import com.itheima.health.constant.RedisMessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.utils.SMSUtils;
import com.itheima.health.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/*
* 发送验证码
* */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    JedisPool jedisPool;

    //使用手机号发送验证码,并将验证码存放到redis中
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        //接收号码,生成随机验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        //使用阿里云发送验证码
        try{
        //SMSUtils.sendShortMessage(telephone,code.toString());
        System.out.println(code);
        }catch (Exception e){
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将生成的验证码放到redis里
        //setex():将验证码存按照键值对的方式放到redis里(有销毁时间)
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_ORDER,300,code.toString());

        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }


    //登录的验证码
    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        //接收号码,生成随机验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        //使用阿里云发送验证码
        try{
            //SMSUtils.sendShortMessage(telephone,code.toString());
            System.out.println(code);
        }catch (Exception e){
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将生成的验证码放到redis里
        //setex():将验证码存按照键值对的方式放到redis里(有销毁时间)
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_LOGIN,300,code.toString());

        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
