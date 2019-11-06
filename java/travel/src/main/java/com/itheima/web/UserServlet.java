package com.itheima.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.damain.ResultInfo;
import com.itheima.damain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import com.itheima.util.BeanUtils;
import com.itheima.util.MailUtils;
import com.itheima.util.UUIDUtils;
import com.itheima.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns="/user", name="UserServlet")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
    //注册功能
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Ajax传参过
        ResultInfo info = null;
        try {
            String check = request.getParameter("check");
            //获取验证码
            String server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
            if (check.equalsIgnoreCase(server)){
                //验证码正确,注册用户
                //1.接收参数
                Map<String, String[]> map = request.getParameterMap();
                //2.封装实体:封装成user对象
                User user = BeanUtils.populate(map, User.class);
                user.setStatus("N");//设置激活状态为N
                user.setCode(UUIDUtils.getUuid());//设置激活码:唯一的字符串
                //3.完成功能:调用service注册,返回boolean类型,是否注册成功
                boolean success = userService.register(user);
                if (success){//如果注册成功,发送激活邮件
                    String url ="http://localhost:80/travel/user?action=active&code=" + user.getCode();
                    String content = "你好,"+user.getName()+":你的账号已经注册成功,<a href='"+url+"'>请点击激活后重新登录</a>";
                    MailUtils.sendMail(user.getEmail(),content);
                }
                //4.处理结果
                 info = new ResultInfo(success);

            }else {
                //验证码错误,返回错误信息:验证码错误,注册失败
                 info = new ResultInfo(false,"验证码错误");
            }
        } catch (Exception e) {
             info = new ResultInfo(false,"系统繁忙,请稍后重试");
            e.printStackTrace();
        }
        //把结果转换成json
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        response.getWriter().print(json);

    }
    //激活功能
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String code = request.getParameter("code");//激活码
        //2.封装实体:略
        //3.完成功能:调用service
        boolean success = userService.active(code);
        //4.处理结果:激活成功,重定向跳转(没有数据要传递)
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
    //登录功能
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = null;
        try {
            //校验验证码
            String check = request.getParameter("check");
            String server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
            if (check.equalsIgnoreCase(server)){//验证码正确
                //1.接收参数
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                //2.封装对象:略
                //3.完成功能
                User user = userService.login(username,password);
                //4.处理结果
                if (user != null) {
                    if ("Y".equals(user.getStatus())){//如果已激活
                        request.getSession().setAttribute("loginUser",user);
                        info=new ResultInfo(true);
                    }else {
                        info = new ResultInfo(false,"用户未激活");
                    }
                }else{
                    info=new ResultInfo(false,"用户名或密码错误");
                }
            }else{//验证码错误
                info = new ResultInfo(false,"验证码错误");
            }
        } catch (Exception e) {
            info= new ResultInfo(false,"系统繁忙,请重试");
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        response.getWriter().print(json);
    }
    public void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info =null;
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser != null) {
            //已登录状态
            info = new ResultInfo(true,loginUser.getName(),"");
        }else{
            info = new ResultInfo(false);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        response.getWriter().print(json);
    }
    //退出登录
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session对象
        request.getSession().invalidate();
        //2.跳转到登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    //校验用户名是否有效
    /*有效返回true,用户名不存在
    * 无效返回false,用户名存在*/
    public void isValid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        //用户名存在是true,不存在是false
        boolean isExist = userService.isExist(username);
        response.getWriter().print(!isExist);

    }
}
