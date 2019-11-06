package com.itheima.domain;

import java.util.List;

public class PageBean<T> {
    /*数据列表*/
    private List<T> dataList;
    /*总页数*/
    private int pageCount;
    /*当前页码*/
    private int pageNumber;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
