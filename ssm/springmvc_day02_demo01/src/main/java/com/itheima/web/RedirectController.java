package com.itheima.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "user")
public class RedirectController {
    @RequestMapping(path = "redirect")
    public String redirectOne(){
        System.out.println("重定向跳转到其他页面");
        return "redirect:/hello03.jsp";
    }

    @RequestMapping(value = "redirect02")
    public String redirectTwo(){
        System.out.println("重定向跳转到其他Controller");
        return "redirect:/other/helloOther";
    }
}
