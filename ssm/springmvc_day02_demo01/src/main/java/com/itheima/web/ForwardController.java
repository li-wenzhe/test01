package com.itheima.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

@Controller
@RequestMapping("user")
public class ForwardController implements Serializable{
    @RequestMapping(path = "forward01")
    public String forward(){
        System.out.println("forward转发到页面");
        return "forward:/WEB-INF/pages/hello.jsp";
    }

    @RequestMapping(path = "forward02")
    public String forwardTwo(){
        System.out.println("forward转发到其他Controller");
        return "forward:/other/helloOther";
    }
}
