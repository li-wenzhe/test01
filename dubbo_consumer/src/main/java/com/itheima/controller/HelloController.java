package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("hello")
public class HelloController {

    @Reference
    HelloService helloService;

    @RequestMapping("sayHello")
    public String sayHello(String name){
        System.out.println("【服务消费者】，调用HelloController类中的sayHello方法，传递的参数是："+name);
        String value = helloService.sayHello(name);
        return value;
    }
}
