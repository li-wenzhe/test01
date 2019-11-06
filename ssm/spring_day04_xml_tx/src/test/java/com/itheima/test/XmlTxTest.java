package com.itheima.test;

import com.itheima.jopo.Account;
import com.itheima.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class XmlTxTest {
    @Resource(name = "accountService02")
    private AccountService accountService02;
    @Test
    public void testTrans(){
        Account from = accountService02.findById(1);
        Account to = accountService02.findById(2);
        accountService02.trans(from,to,100.0);
        
    }
}
