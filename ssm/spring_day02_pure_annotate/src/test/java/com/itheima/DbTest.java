package com.itheima;

import com.itheima.bean.Account;
import com.itheima.config.SpringConfiguration;
import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class DbTest {

    @Test
    public void fun01() throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        AccountService accountService = applicationContext.getBean("accountServiceImpl",AccountService.class);
        List<Account> list = accountService.findAll();
        System.out.println(list);
    }

    @Test
    public void funById() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = applicationContext.getBean("accountService",AccountService.class);
        Account account = accountService.findById(2);
        System.out.println(account);
    }
}
