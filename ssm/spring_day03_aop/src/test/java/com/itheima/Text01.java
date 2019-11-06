package com.itheima;

import com.itheima.dao.AccountDao;
import com.itheima.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Text01 {
    @Autowired
    private AccountService accountService;


    @Test
    public void testBefore(){
        accountService.save();
    }

    @Test
    public void testDelete(){
        accountService.delete();
    }

    @Test
    public void showTime(){
        accountService.addAccount();
    }

    @Test
    public void showException(){
        accountService.queryAll();
    }

    @Test
    public void after(){
        accountService.queryById();
    }
}
