package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "WordFilter",value = "/*")
public class WordSuperFilter implements Filter {
    private List<String> words = new ArrayList<>();

    public void init(FilterConfig config) throws ServletException {
        InputStream is = null;
        BufferedReader br = null;

        try {
            //读取资源文件words.txt.用ServletCont
            ServletContext context = config.getServletContext();
            is = context.getResourceAsStream("WEB-INF/words.txt");//获取流

            br = new BufferedReader(new InputStreamReader(is,"utf-8"));//字符流转换成字节流

            //其中每一行是一个敏感字符,添加到words集合里
            String line =null;
            while((line = br.readLine())!=null){//line为一行字符
                words.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //创建request对象的包装类
        MyRequest myRequest = new MyRequest(request,words);

        chain.doFilter(myRequest, resp);
    }



}
