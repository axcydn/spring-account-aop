package com.service.impl;

import com.dao.IAccountDao;
import com.domain.Account;
import com.service.IAccountService;
import org.aspectj.lang.annotation.Aspect;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : gxm
 * @date : 2020/5/5
 * @time : 21:07
 * @projectName : spring-account
 * @description :
 * To change this template use File | Settings | File Templates.
 * Description:
 **/

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;
    @Override
    public void transfer(Account account1, Account account2,Float money) {
        System.out.println("AccountServiceImpl");
        // 1. 获取账户1的信息
        List<Account> accounts1 = queryById(account1.getId());
        // 2. 获取账户2的信息
        List<Account> accounts2 = queryById(account2.getId());
        // 3. 转出
        for (Account account : accounts1) {
            account.setBalance(account.getBalance()-money);
        }

        // 4. 转入
        for (Account account : accounts2) {
            account.setBalance(account.getBalance()+money);
        }
        // 5. 更新数据库
        accounts1.forEach(this::updateAccount);
        int a = 1/0;
        accounts2.forEach(this::updateAccount);
    }

    @Override
    public List<Account> queryAll() {
        return accountDao.queryAll();
    }

    @Override
    public List<Account> queryById(Integer id) {
        return accountDao.queryById(id);
    }

    @Override
    public List<Account> queryByName(String name) {
        return accountDao.queryByName(name);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccountById(Integer id) {
        accountDao.deleteAccountById(id);
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
