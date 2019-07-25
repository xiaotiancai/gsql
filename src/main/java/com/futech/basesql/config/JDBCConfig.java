/**
 * @des 有问题请留言github或者邮箱找我
 * @author 719383495@qq.com
 * @date 2019/7/25 23:22
 */
package com.futech.basesql.config;

import com.futech.basesql.utils.Res;
import com.futech.basesql.utils.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class JDBCConfig {

    private static final Logger logger = LoggerFactory.getLogger(JDBCConfig.class);
    private ReentrantLock lock = new ReentrantLock();

    private List<PoolConnection> connPool = Collections.synchronizedList(new ArrayList<>());

    private final static int INIT_SIZE = 2;
    private final static int MAX_SIZE = 4;
    private final static int STEP_SIZE = 1;
    private final static int TIMEOUT = 2000;

    private ConnectProperties properties;


    public PoolConnection getConnection() {
        PoolConnection pc = null;
        try {
            lock.lock();
            if (0 == connPool.size()) {
                createConnection(INIT_SIZE);
            }
            pc = getAvailableConnection();

            while (null == pc) {
                logger.info("wait for connection ···");
                createConnection(STEP_SIZE);
                pc = getAvailableConnection();
                if (null == pc) {
                    TimeUnit.MILLISECONDS.sleep(30);

                }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return pc;
    }

    public JDBCConfig() {
        ConnectProperties cp = new ConnectProperties();
        properties = cp;
        try {
            Driver driver = (Driver) Class.forName(properties.getDriveName()).newInstance();
            DriverManager.registerDriver(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void createConnection(int count) {
        if (connPool.size() + count <= MAX_SIZE) {
            for (int i = 0; i < count; i++) {
                System.out.println("initialing " + (i + 1) + " connection");
                try {
                    Connection connection = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPwd());
                    PoolConnection pool = new PoolConnection(connection, true);
                    connPool.add(pool);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public PoolConnection getAvailableConnection() {
        try {
            for (PoolConnection pool : connPool) {
                if (pool.isStatus()) {
                    Connection conn = pool.getConn();
                    if (!conn.isValid(TIMEOUT)) {
                        Connection newConn = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPwd());
                        pool.setConn(newConn);
                    }
                    pool.setStatus(false);
                    return pool;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void sqlInsertHandler(String sql){
        PoolConnection pc = getConnection();
        Connection conn = pc.getConn();
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.executeUpdate(sql);
            logger.info("sql execute successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            pc.releaseConnect();
        }
    }



    public Res sqlQueryHandler(String sql, Map<String, String> columnName) {
        PoolConnection pc = getConnection();
        Connection conn = pc.getConn();
        Statement statement;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Map<String, String>> list = new ArrayList<>();

            while (rs.next()) {
                Map<String, String> map = new HashMap();
                for (Map.Entry<String, String> entry : columnName.entrySet()) {
                    if (null == rs.getString(entry.getValue()) || "".equals(rs.getString(entry.getValue()))) {

                    } else {
                        map.put(entry.getKey(), rs.getString(entry.getValue()));
                    }
                }
                list.add(map);
            }
            Res res = new Res(list);
            System.out.println(res);
            return new Res(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            pc.releaseConnect();
        }
        return null;
    }

    public Map queryTableColumnName(String tableName) {
        PoolConnection pc = getConnection();
        Connection conn = pc.getConn();
        PreparedStatement statement = null;
        String sql="select * from "+tableName+" limit 1";
        Map map = new HashMap();
        try {
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            int len = data.getColumnCount();
            for (int i = 1; i <= len; i++) {
                String columnName = data.getColumnName(i);
                map.put(SqlUtil.sqlFieldToCamelCase(columnName), columnName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pc.releaseConnect();
        }
        return map;
    }


}
