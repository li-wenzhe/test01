package com.itheima.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itheima.damain.PageBean;
import com.itheima.damain.Route;

import java.util.List;
import java.util.Map;

public interface RouteService {
    Map<String, List<Route>> usefulRoutes() throws JsonProcessingException;


    PageBean<Route> search(String rname, String cid, int pageNumber, int pageSize);

    Route routeDetail(String rid);
}
