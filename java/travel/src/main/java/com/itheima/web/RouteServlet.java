package com.itheima.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.damain.PageBean;
import com.itheima.damain.ResultInfo;
import com.itheima.damain.Route;
import com.itheima.service.impl.RouteServletImpl;
import com.itheima.service.RouteService;
import com.itheima.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns="/route", name="RouteServlet")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServletImpl();
    public void usefulRoutes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = null;
        try{
            //完成功能
            Map<String,List<Route>> map = routeService.usefulRoutes();
            //处理结果
            info = new ResultInfo(true,map);
        }catch (Exception e){
            info = new ResultInfo(false,"系统繁忙,请稍后重试");
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        response.getWriter().print(json);
    }

    //搜索功能
    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = null;
        try{
            //1.接收参数
            String rname = request.getParameter("rname");
            String cid = request.getParameter("cid");
            int pageNumber = 1;//默认页码
            int pageSize = 8;//每页的个数
            String pageNumberStr = request.getParameter("pageNumber");
            if (pageNumberStr!=null&&!"".equals(pageNumberStr)){
                pageNumber = Integer.parseInt(pageNumberStr);
            }

            //2.完成功能
            PageBean<Route> pageBean = routeService.search(rname,cid,pageNumber,pageSize);

            //3.处理结果
            info = new ResultInfo(true,pageBean);

        }catch (Exception e){
            info = new ResultInfo(false,"search出错");
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        response.getWriter().print(json);
    }

    //查看详情
    public void routeDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = null;
        try{
            //1.接收参数
            String rid = request.getParameter("rid");
            //2.完成功能
            Route route = routeService.routeDetail(rid);
            //3.处理结果
            info = new ResultInfo(true,route);
        }catch (Exception e){
            info = new ResultInfo(false,"routeDetail出错");
            e.printStackTrace();
        }
        ObjectMapper objectMapper =new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        response.getWriter().print(json);
    }
}
