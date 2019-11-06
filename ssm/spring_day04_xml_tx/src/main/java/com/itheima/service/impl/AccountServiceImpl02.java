package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.jopo.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("accountService02")
public class AccountServiceImpl02 implements AccountService {
    /*@Resource(name = "accountDao02")*/
    @Autowired
    private AccountDao accountDao02;

    @Override
    public void save(Account account) {
        accountDao02.save(account);
    }

    @Override
    public void update(Account account) {
        accountDao02.update(account);
    }

    @Override
    public void delete(Integer id) {
        accountDao02.delete(id);
    }

    @Override
    public Account findById(Integer id) {
        return accountDao02.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDao02.findAll();
    }

    @Override
    public Integer count() {
        return accountDao02.count();
    }

    @Override
    public void trans(Account from, Account to,Double money) {
        from.setMoney(from.getMoney()-money);


        to.setMoney(to.getMoney()+money);
        accountDao02.update(from);
        int i = 1/0;
        accountDao02.update(to);

    }
}
