package com.itheima.damain;

/*用于封装收藏排行榜的搜索条件
* 参数有:
*      rname:搜索的旅游路线
*      minprice:最小金额
*      maxprice:最大金额
* */
public class QueryOV {
    private String rname;
    private String minprice;
    private String maxprice;

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getMinprice() {
        return minprice;
    }

    public void setMinprice(String minprice) {
        this.minprice = minprice;
    }

    public String getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(String maxprice) {
        this.maxprice = maxprice;
    }
}
