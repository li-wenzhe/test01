package com.itheima.web;

import com.itheima.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

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
        //查询所有用户
        List<User> userList= userService.queryAll();
        //处理结果
        request.setAttribute("userList",userList);//把数据放进域里
        //请求转发
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //异常在Servlet不能抛出

        try {
            //1.接收参数
            Map<String, String[]> map = request.getParameterMap();
            //2.封装实体
            User user = new User();//new一个user对象
            BeanUtils.populate(user,map);//把map封装到user里
            //3.完成功能:调用Service,添加用户
            boolean success = userService.add(user);
            //4.处理结果:重定向跳转(没有要传递的数据)
            response.sendRedirect(request.getContextPath()+"/user?action=queryAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void editPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数:接收修改用户的id
        String id = request.getParameter("id");
        //2.封装实体:只有一个参数,不用封装
        //3.完成功能:调用service得到用户信息
        User user=userService.findById(id);
        //4.处理结果:把数据放进域里,再请求跳转到update页面
        request.setAttribute("user",user);
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //1.接收数据:有多个参数,用map
            Map<String, String[]> map = request.getParameterMap();
            //2.封装实体
            User user = new User();
            BeanUtils.populate(user,map);//把map集合装进user对象里
            //3.完成功能:条用Service
            boolean edit = userService.edit(user);
            //4.处理结果:简单处理,重定向跳转
            response.sendRedirect(request.getContextPath()+"/user?action=queryAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String id = request.getParameter("id");
        //2.封装实体:只有一个参数不用封装
        //3.完成功能
        boolean delete = userService.delete(id);
        //4.处理结果:简单处理,重定向跳转
        response.sendRedirect(request.getContextPath()+"/user?action=queryAll");
    }
}