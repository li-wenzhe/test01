package com.itheima.web;

import com.itheima.jopo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("response")
public class JsonController {
    @RequestMapping("json")
    @ResponseBody
    public User responseJson(User user){
        System.out.println("收到请求-------------------->"+user);
        user.setName("李四");
        return user;
    }

}
