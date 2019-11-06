package com.itheima.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.damain.ResultInfo;
import com.itheima.service.CategoryService;
import com.itheima.service.impl.CategoryServiceImpl;
import com.itheima.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryServlet",urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryServiceImpl();
    public void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = null;
        try{
            //完成功能
            String count = categoryService.queryAll();
            //处理结果
            info = new ResultInfo(true,count,"");
        }catch (Exception e){
            info = new ResultInfo(false,"系统繁忙");
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        response.getWriter().print(json);
    }
}
