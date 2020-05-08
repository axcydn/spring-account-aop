package com.service;

import com.dao.IAccountDao;
import com.domain.Account;

/**
 * @author gxm
 * @date 2020/5/5 21:05
 * @description This is description of class
 * @since version-1.0
 */
public interface IAccountService extends IAccountDao {
/**
 * 支持转账
 * @author gxm
 * @date 2020/5/5 21:11
 * @description This is description of method
 * @param account1 1
 * @param account2 2
 * @param money 3
 * @return void
 * @since version-1.0
 */
    void transfer(Account account1,Account account2,Float money);
}
