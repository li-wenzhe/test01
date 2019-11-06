package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("【服务提供者】：HelloServiceImpl中的sayHello方法:传递的参数是:"+name);

        return "hello"+name;
    }
}
