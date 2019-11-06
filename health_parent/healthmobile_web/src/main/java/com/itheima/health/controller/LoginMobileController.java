package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.constant.RedisMessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Member;
import com.itheima.health.pojo.Order;
import com.itheima.health.service.MemberService;
import com.itheima.health.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginMobileController {
    @Reference
    MemberService memberService;

    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/check")
    public Result check(@RequestBody Map map, HttpServletResponse response){
        //1.获取手机号和验证码
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");

        //2.校验验证码
        String redisCode = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        if (redisCode==null||!redisCode.equals(validateCode)){
            //验证码错误
            return new Result(false,MessageConstant.VALIDATECODE_ERROR);
        }

        //3.根据手机号码判断是否为会员,不是会员则注册
        Member member = memberService.findMemberByTelephone(telephone);
        if (member==null){
            //用户不是会员,自动注册
            member = new Member();
            member.setRegTime(new Date());
            member.setPhoneNumber(telephone);
            memberService.add(member);
        }

        //4.登录成功:把数据存放到Cookie里
        Cookie cookie = new Cookie("login_member_telephone",telephone);//cookie里面存的是键值对
        cookie.setPath("/");//有效路径
        cookie.setMaxAge(30*24*60*60);//有效时间
        response.addCookie(cookie);

        return new Result(true,MessageConstant.LOGIN_SUCCESS);
    }
}
