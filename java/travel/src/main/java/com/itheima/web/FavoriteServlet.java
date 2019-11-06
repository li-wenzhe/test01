package com.itheima.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.damain.*;
import com.itheima.service.FavoriteService;
import com.itheima.service.RouteService;
import com.itheima.service.impl.FavoriteServiceImpl;
import com.itheima.service.impl.RouteServletImpl;
import com.itheima.util.BeanUtils;
import com.itheima.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet(urlPatterns="/favorite", name="FavoriteServlet")
public class FavoriteServlet extends BaseServlet {
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    private RouteService routeService = new RouteServletImpl();

    //判断是否已收藏
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = null;
        try{
            //判断是否登录:从session域里面取数据
            User user = (User) request.getSession().getAttribute("loginUser");
            if (user == null) {//为空则未登录,也就是未收藏
                info =new ResultInfo(true,false);
            }else{//已登录
                //1.接收参数
                String rid = request.getParameter("rid");
                //2.完成功能:是否收藏了
                Boolean isFavorite = favoriteService.isFavorite(rid,user);
                //3.处理结果
                info = new ResultInfo(true,isFavorite);
            }
        }catch (Exception e){
            e.printStackTrace();
            info = new ResultInfo(false,"isFavorite出错");
        }
        ObjectMapper objectMapper =new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        response.getWriter().print(json);
    }

    //添加收藏
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = null;
        try{
            //判断是否登录:从session域里面取数据
            User user = (User) request.getSession().getAttribute("loginUser");
            if (user == null) {//为空则未登录,也就是未收藏
                info = new ResultInfo(true, -1);
            }else{//已登录
                String rid = request.getParameter("rid");
                //封装实体
                Favorite favorite = new Favorite();
                favorite.setRid(rid);
                favorite.setUid(user.getUid());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                favorite.setDate(simpleDateFormat.format(new Date()));

                //完成功能
                boolean success = favoriteService.addFavorite(favorite);
                if (success){//收藏成功
                    Route route = routeService.routeDetail(rid);
                    info = new ResultInfo(true,route.getCount());
                }else {
                    info = new ResultInfo(false,"收藏失败");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            info = new ResultInfo(false,"isFavorite出错");
        }
        ObjectMapper objectMapper =new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        response.getWriter().print(json);
    }

    //我的收藏
    public void myFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = null;
        try{
            //1.判断用户登录状态:根据session
            User user = (User) request.getSession().getAttribute("loginUser");
            if (user == null) {//为空则未登录,也就是未收藏
                info = new ResultInfo(true, -1);
            }else {//已登录
                String pageNumberStr = request.getParameter("pageNumber");
                Integer pageNumber = 1;
                Integer pageSize = 8;
                if (pageNumberStr!=null&&!"".equals(pageNumberStr)){
                    pageNumber = Integer.parseInt(pageNumberStr);
                }
                //完成功能
                PageBean<Route> pageBean = favoriteService.myFavorite(user,pageNumber,pageSize);
                //处理结果
                info = new ResultInfo(true,pageBean);
            }
        }catch(Exception e){
            e.printStackTrace();
            info = new ResultInfo(false,"myFavorite出错");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        response.getWriter().print(json);
    }

    //收藏排行榜相关功能
    public void favoriteRank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info =null;
        try{
            //1.1 接收并封装搜索条件的参数
            Map<String, String[]> map = request.getParameterMap();//把接收的参数放到map集合里
            //调用BeanUtils的封装方法
            QueryOV ov = BeanUtils.populate(map, QueryOV.class);//把map集合封装到QueryOV对象
            //1.2 接收分页条件的参数
            String pageNumberStr = request.getParameter("pageNumber");
            int pageNumber = 1;
            int pageSize = 8;
            if (pageNumberStr!=null&&!"".equals(pageNumberStr)){
                pageNumber = Integer.parseInt(pageNumberStr);//把String类型转换为int
            }

            //2.完成功能
            PageBean<Route> pageBean = favoriteService.favoriteRank(ov,pageNumber,pageSize);/*有分页的用PageBean接收*/

            //3. 处理结果
            info =new ResultInfo(true,pageBean);
        }catch(Exception e){
            e.printStackTrace();
            info =new ResultInfo(false,"favoriteRank出错");
        }
        ObjectMapper objectMapper =new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        response.getWriter().print(json);
    }
}
