package com.dao.impl;

import com.dao.IAccountDao;
import com.domain.Account;
import com.util.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : gxm
 * @date : 2020/5/5
 * @time : 20:06
 * @projectName : spring-account
 * @description :
 * To change this template use File | Settings | File Templates.
 * Description:
 **/
public class AccountDaoImpl implements IAccountDao {
    private QueryRunner queryRunner;
    private ConnectionUtils connectionUtils;

    @Override
    public List<Account> queryAll() {
        List<Account> accountList = null;
        try {
            String sql = "select * from Account.userInfo";
            accountList = queryRunner.query(connectionUtils.getThreadConnection(), sql, new BeanListHandler<>(Account.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }

    @Override
    public List<Account> queryById(Integer id) {
        try {
            String sql = "select * from Account.userInfo where id=?";
            List<Account> resultList = queryRunner.query(connectionUtils.getThreadConnection(), sql, new BeanListHandler<>(Account.class), id);
            return resultList;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public List<Account> queryByName(String name) {
        try {
            String sql = "select * from Account.userInfo where name=?";
            List<Account> resultList = queryRunner.query(connectionUtils.getThreadConnection(), sql, new BeanListHandler<>(Account.class), name);
            return resultList;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            String sql = "insert into Account.userInfo(name,balance) values (?,?)";
            queryRunner.update(connectionUtils.getThreadConnection(), sql, account.getName(), account.getBalance());
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            String sql = "update Account.userInfo set name=?, balance=? where id=?";
            Connection threadConnection = connectionUtils.getThreadConnection();
            queryRunner.update(connectionUtils.getThreadConnection(), sql, account.getName(), account.getBalance(), account.getId());
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void deleteAccountById(Integer id) {
        try {
            String sql = "delete from Account.userInfo where id=?";
            queryRunner.update(connectionUtils.getThreadConnection(), sql, id);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public void setQueryRunner(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }
}
