package com.itheima.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.List;

public class MyRequest extends HttpServletRequestWrapper{
    private List<String> words;
    public MyRequest(HttpServletRequest request, List<String> words) {
        super(request);
        this.words=words;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (value != null&&!"".equals(value)) {
            for (String word : words) {
                value = value.replace(word,"**");
            }
        }
        return value;
    }
}
