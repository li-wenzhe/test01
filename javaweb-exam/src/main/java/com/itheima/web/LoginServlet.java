package com.itheima.web;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(urlPatterns="/login", name="loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
       response.setContentType("text/html;charset=utf-8");
        //接收参数
        Map<String, String[]> map = request.getParameterMap();
        //封装实体
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //完成功能
        User user1 = userService.addUser(user);
        //处理结果
        if (user1 != null){
            //登陆成功，记住登录信息
            Cookie username = new Cookie("username",user1.getUsername());
            Cookie password = new Cookie("password",user1.getPassword());

            if (request.getParameter("remember-me") == null){
                //不记住登录信息，清除登录cookie
                username.setMaxAge(0);
                password.setMaxAge(0);
            }
            response.addCookie(username);
            response.addCookie(password);
            response.sendRedirect(request.getContextPath() + "/success.html");

        }else { //登陆失败
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
