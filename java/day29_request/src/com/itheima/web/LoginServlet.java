package com.itheima.web;

import com.itheima.domain.User;
import com.itheima.util.JdbcUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns="/login", name="LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数:username,password
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String check = request.getParameter("check");

        //2.完成功能:
        //首先校验验证码
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        //如果验证码错误,返回错误信息,后面代码就不用执行
        if (!check.equalsIgnoreCase(checkcode_server)){
            request.setAttribute("loginError","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }


        // 校验username和password是否正确,执行SQL语句
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from user where username=? and password=?";



        /*try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (DataAccessException e) {
//            e.printStackTrace();
            System.out.println("登录失败");
        }*/

        /*User user =null;
        System.out.println(user);
        List<User> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        if (query != null&&query.size()>0) {
            user = query.get(0);
        }
        System.out.println(user);*/

        //3.处理结果:判断登录是否成功
        Map<String, Object> map = null;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, username, password);
        if (maps!=null&&maps.size()>0){
            map=maps.get(0);
        }
        if (map != null) {
            //说明登录成功,重定向跳转
            response.sendRedirect(request.getContextPath()+"/index.jsp");

        }else{
            //登录失败,把原因放到request域对象里,跳转到登录页面
            request.setAttribute("loginError","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);

    }
        }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}