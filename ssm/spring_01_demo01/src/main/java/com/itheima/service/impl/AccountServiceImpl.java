package com.itheima.service.impl;

import com.itheima.bean.Account;
import com.itheima.dao.AccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    /*private static  ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    private  static AccountDao accountDao = applicationContext.getBean("accountDao",AccountDao.class);*/


    private AccountDao accountDao;

    //    set方法注入的属性构造方法也能注入
    /*public AccountServiceImpl(AccountDao accountDao){
        this.accountDao = accountDao;
    }*/
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void save(Account account) throws Exception {
        accountDao.save(account);

    }

    @Override
    public void update(Account account) throws Exception {
        accountDao.update(account);

    }

    @Override
    public void delete(Integer id) throws Exception {
        accountDao.delete(id);
    }

    @Override
    public Account findById(Integer id) throws Exception {
        return accountDao.findById(id);
    }

    @Override
    public List<Account> findAll() throws Exception {
        //AccountDao accountDao = new AccountDaoImpl();
        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountDao accountDao = applicationContext.getBean("accountDao",AccountDao.class);*/
        return accountDao.findAll();
    }
}
