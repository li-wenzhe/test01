package com.itheima.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(urlPatterns="/demo1", name="AjaxServlet")
public class AjaxServlet extends HttpServlet {
    private UserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数action
        String action = request.getParameter("action");

        //根据action的值来执行对应的方法
        //1.获取Class对象
        Class aClass = this.getClass();
        //2.反射调用普通方法,先获取Method,再反射调用Method
        try {
            Method method = aClass.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    public void isExist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数:name
        String name = request.getParameter("name");
        //2.封装实体:略
        //3.完成功能:调用service,判断name是否存在
        boolean isExist = userService.isExist(name);
        //4.处理结果:异步(不能跳转),直接返回响应isExist
        response.getWriter().print(isExist);
    }
    public void Search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数:name
        String name = request.getParameter("name");
        //2.封装实体:略
        //3.完成功能:调用service,得到根据name搜索的结果
        List<User> userSearch = userService.search(name);
        //4.处理结果:异步(不能跳转),把结果转换成json格式的字符串,返回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userSearch);
        response.getWriter().print(json);
    }
}
