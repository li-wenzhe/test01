package com.itheima;

import com.itheima.beans.Account;
import com.itheima.dao.AccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JdbcTest {
    @Autowired
    private AccountDao accountDao02;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AccountDao accountDao03;

    @Test
    public void testFindAll(){
        List<Account> accounts = accountDao.queryAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
    @Test
    public void testFindAll02(){
        List<Account> accounts = accountDao02.queryAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }@Test
    public void testFindAll03(){
        List<Account> accounts = accountDao03.queryAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
