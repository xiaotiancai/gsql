/**
 * @des 有问题请留言github或者邮箱找我
 * @author 719383495@qq.com
 * @date 2019/7/25 23:22
 */
package com.futech.basesql.config;

import java.sql.Connection;

public class PoolConnection {

    private Connection conn;
    private boolean status;

    public PoolConnection() {

    }

    public PoolConnection(Connection conn, boolean status) {
        this.conn = conn;
        this.status = status;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public void releaseConnect() {

        this.status = true;
    }
}
