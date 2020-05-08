package com.dao;

import com.domain.Account;

import java.util.List;

/**
 * @author gxm
 * @date 2020/5/5 20:01
 * @description This is description of class
 * @since version-1.0
 */
public interface IAccountDao {
    /**
     *  查询所有的账户
     * @author gxm
     * @date 2020/5/5 20:02
     * @description
     * @return java.util.List<com.domain.Account>
     * @since version-1.0
     */
    List<Account> queryAll();
    /**
     * 根据ID查询
     * @author gxm
     * @date 2020/5/5 20:03
     * @description This is description of method
     * @param id 1
     * @return java.util.List<com.domain.Account>
     * @since version-1.0
     */
    List<Account> queryById(Integer id);
    /**
     * 根据名称查询
     * @author gxm
     * @date 2020/5/5 20:03
     * @description This is description of method
     * @param name 1
     * @return java.util.List<com.domain.Account>
     * @since version-1.0
     */
    List<Account> queryByName(String name);
    /**
     * 把账户写入数据库
     * @author gxm
     * @date 2020/5/5 20:04
     * @description This is description of method
     * @param account 1
     * @return void
     * @since version-1.0
     */
    void saveAccount(Account account);
    /**
     * 更新账户
     * @author gxm
     * @date 2020/5/5 20:05
     * @description This is description of method
     * @param account 1
     * @return void
     * @since version-1.0
     */
    void updateAccount(Account account);
    /**
     * 根据ID删除用户
     * @author gxm
     * @date 2020/5/5 20:06
     * @description This is description of method
     * @param id 1
     * @return void
     * @since version-1.0
     */
    void deleteAccountById(Integer id);
}
