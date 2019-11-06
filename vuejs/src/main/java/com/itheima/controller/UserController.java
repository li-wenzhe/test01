package com.itheima.controller;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    // 使用restful接收id属性值;restful风格：url="user/findOne/10"
    @RequestMapping("/findOne/{id}")
    public User findOne(@PathVariable("id") Integer id){
        return userService.findOne(id);
    }

    @RequestMapping("/update")
    public void update(@RequestBody User user){//把json类型转换为实体类
        userService.update(user);
    }
}
