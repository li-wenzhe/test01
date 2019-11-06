package com.itheima.web;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(urlPatterns="/user", name="UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            //1.接收参数action:要执行的方法名称
            String action = request.getParameter("action");

            //判断action的值,执行对应的方法:action值是什么就执行什么方法
            Class aClass = this.getClass();
            /*反射获取方法*/
            Method method = aClass.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);//反射调用方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    public void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList= userService.queryAll();
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String pageNumberStr = request.getParameter("pageNumber");
        int pageNumber = Integer.parseInt(pageNumberStr);//把字符串类型转为int类型
        int pageSize = 5;//每页显示5条
        //3.完成功能
        PageBean<User> pageBean = userService.pageQuery(pageNumber,pageSize);

        //4.处理结果:请求转发
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
}