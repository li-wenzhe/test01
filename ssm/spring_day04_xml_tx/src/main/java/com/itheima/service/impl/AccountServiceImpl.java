package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.jopo.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Override
    public void save(Account account) {
        accountDao.save(account);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public void delete(Integer id) {
        accountDao.delete(id);
    }

    @Override
    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Integer count() {
        return accountDao.count();
    }

    @Override
    public void trans(Account from, Account to,Double money) {
        from.setMoney(from.getMoney()-money);
        int i = 1/0;

        to.setMoney(to.getMoney()+money);
        accountDao.update(from);
        accountDao.update(to);

    }
}
