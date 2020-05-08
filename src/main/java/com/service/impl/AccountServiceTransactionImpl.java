package com.service.impl;

import com.dao.IAccountDao;
import com.domain.Account;
import com.service.IAccountService;
import com.util.TransactionManager;

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
public class AccountServiceTransactionImpl implements IAccountService {
    private IAccountDao accountDao;
    private TransactionManager transactionManager;
    @Override
    public void transfer(Account account1, Account account2, Float money) {
        System.out.println("AccountServiceTransactionImpl");
        try {
            transactionManager.beginTransaction();
            // 1. 获取账户1的信息
            List<Account> accounts1 = accountDao.queryById(account1.getId());
            // 2. 获取账户2的信息
            List<Account> accounts2 = accountDao.queryById(account2.getId());
            // 3. 转出
            for (Account account : accounts1) {
                account.setBalance(account.getBalance() - money);
            }
            // 4. 转入
            for (Account account : accounts2) {
                account.setBalance(account.getBalance() + money);
            }
            // 5. 更新数据库
            accounts1.forEach((account -> accountDao.updateAccount(account)));
            //int a = 1 / 0;
            accounts2.forEach((account -> accountDao.updateAccount(account)));
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollBack();
            throw new RuntimeException(e);
        } finally {
            transactionManager.release();
        }
    }

    @Override
    public List<Account> queryAll() {
        try {
            transactionManager.beginTransaction();
            List<Account> accounts = accountDao.queryAll();
            transactionManager.commit();
            return accounts;
        } catch (Exception e) {
            transactionManager.rollBack();
            throw new RuntimeException(e);
        } finally {
            transactionManager.release();
        }
    }

    @Override
    public List<Account> queryById(Integer id) {
        try {
            transactionManager.beginTransaction();
            List<Account> accounts = accountDao.queryById(id);
            transactionManager.commit();
            return accounts;
        } catch (Exception e) {
            transactionManager.rollBack();
            throw new RuntimeException(e);
        } finally {
            transactionManager.release();
        }
    }

    @Override
    public List<Account> queryByName(String name) {
        try {
            transactionManager.beginTransaction();
            List<Account> accounts = accountDao.queryByName(name);
            transactionManager.commit();
            return accounts;
        } catch (Exception e) {
            transactionManager.rollBack();
            throw new RuntimeException(e);
        } finally {
            transactionManager.release();
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            transactionManager.beginTransaction();
            accountDao.saveAccount(account);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollBack();
            throw new RuntimeException(e);
        } finally {
            transactionManager.release();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            transactionManager.beginTransaction();
            accountDao.updateAccount(account);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollBack();
            throw new RuntimeException(e);
        } finally {
            transactionManager.release();
        }
    }

    @Override
    public void deleteAccountById(Integer id) {
        try {
            transactionManager.beginTransaction();
            accountDao.deleteAccountById(id);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollBack();
            throw new RuntimeException(e);
        } finally {
            transactionManager.release();
        }
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
