package com.itheima.test;

import com.itheima.health.utils.DateUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.encode("123"));
        try {
            System.out.println(DateUtils.parseDate2String(DateUtils.getThisWeekMonday()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
