package com.itheima.web;

import com.itheima.jopo.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
/*@RequestMapping(value = "/account")*/
public class HelloController {
    @RequestMapping(value = "/hello")
    public String sayHello(){
        System.out.println("sayHello");
        return "success";
    }

    @RequestMapping(value = "/list")
    public String list(String name,double money ){
        System.out.println("name:"+name+"------------money:"+money);
        return "success";
    }
    @RequestMapping(value = "/add33",method = RequestMethod.POST)
    public String add(Account account){
        System.out.println("--------------->"+account);
        return "success";
    }
    @RequestMapping(value = "/update33",method = RequestMethod.POST)
    public String update(Account account){
        System.out.println("22222222222--------------->"+account);
        return "success";
    }

}
