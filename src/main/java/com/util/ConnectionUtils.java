package com.util;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : gxm
 * @date : 2020/5/5
 * @time : 20:21
 * @projectName : spring-account
 * @description : 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 * To change this template use File | Settings | File Templates.
 * Description:
 **/
public class ConnectionUtils {
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private DataSource dataSources;

    /**
     * @param
     * @return java.sql.Connection
     * @author gxm
     * @date 2020/5/5 20:30
     * @description 获取当前线程的连接，如果没有连接则从连接池中取一个
     * @since version-1.0
     */
    public Connection getThreadConnection() {
        try {
            Connection connection = threadLocal.get();
            if (connection == null) {
                connection = dataSources.getConnection();
                threadLocal.set(connection);
            }
            return connection;
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public void setDataSources(DataSource dataSources) {
        this.dataSources = dataSources;
    }
    /**
     * @author gxm
     * @date 2020/5/5 20:31
     * @description 将线程和当前连接解绑
     * @param
     * @return void
     * @since version-1.0
     */
    public void remove(){
        threadLocal.remove();
    }
}
