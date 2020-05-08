package com.factory;

import com.service.IAccountService;
import com.util.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : gxm
 * @date : 2020/5/5
 * @time : 21:04
 * @projectName : spring-account
 * @description : 对普通方法增加事务功能 以及获取已经添加事务功能的服务层对象
 * To change this template use File | Settings | File Templates.
 * Description:
 **/
public class BeanFactory {
    private IAccountService service;
    private TransactionManager transactionManager;

    /**
     * @param
     * @return com.service.IAccountService
     * @author gxm
     * @date 2020/5/5 21:38
     * @description 添加事务操作的服务对象工厂法, 需要对所有需要事务操作的方法逐个添加
     * @since version-1.0
     */
    public IAccountService getAccountService() {
        return (IAccountService) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {
                    transactionManager.beginTransaction();
                    Object rtValue = method.invoke(service, args);
                    transactionManager.commit();
                    return rtValue;
                } catch (Exception e) {
                    transactionManager.rollBack();
                    throw new RuntimeException(e);
                } finally {
                    transactionManager.release();
                }
            }
        });
    }
    public void setService(IAccountService service) {
        this.service = service;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
