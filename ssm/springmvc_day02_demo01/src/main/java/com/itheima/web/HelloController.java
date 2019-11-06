package com.itheima.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

@Controller
@RequestMapping(value = "other")
public class HelloController implements Serializable {

    @RequestMapping(value = "helloOther")
    public String otherController(){
        System.out.println("其他方法");
        return "hello02";
    }
}
