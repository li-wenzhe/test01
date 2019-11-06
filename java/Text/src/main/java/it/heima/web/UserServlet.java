package it.heima.web;

import it.heima.service.UserService;
import it.heima.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns="/user", name="UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        //2.完成功能
        boolean ifLogin = userService.login(username,password);
        response.getWriter().print(ifLogin);

        /*if (ifLogin){//登录成功
            response.sendRedirect("success.html");
        }else{//登录失败
            response.sendRedirect("error.html");
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
