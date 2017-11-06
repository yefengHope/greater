package com.hf.engine.buildCode.jdbc;


import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>@Title jdbc连接 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/4/20 10:52 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public abstract class AbstractJdbcConnection {

    private static final long serialVersionUID = -5810896424801558252L;
    private static Logger logger = Logger.getLogger(AbstractJdbcConnection.class);

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;


    /**
     * 获取jdbc Connection
     * 没有关闭连接 请调用closeConnection()
     *
     * @return Connection
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException           SQLException
     */
    public Connection getConnection(JdbcConfig jdbcConfig) throws ClassNotFoundException, SQLException {

        // 注册 JDBC 驱动
        Class.forName(jdbcConfig.getJdbcDriver());

        // 打开链接
        logger.info("连接数据库...");
        conn = DriverManager.getConnection(jdbcConfig.getDbUrl(), jdbcConfig.getUser(), jdbcConfig.getPass());

        return conn;
    }

    /**
     * 根据sql获取结果集
     * 没有关闭连接 请调用closeConnection()
     *
     * @param sql sql
     * @return ResultSet
     */
    public void getResultSet(JdbcConfig jdbcConfig,String sql) throws SQLException, ClassNotFoundException {
        conn = getConnection(jdbcConfig);
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
    }

    /**
     * 关闭连接
     *
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        if (null != rs) {
            rs.close();
        }

        if (null != stmt) {
            stmt.close();
        }

        if (null != conn) {
            conn.close();
        }
    }

    /**
     * 解析表格内容
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> parseResultSet() throws SQLException {
        ResultSetMetaData m = rs.getMetaData(); //获取 列信息
        int columns = m.getColumnCount();

        List<Map<String, Object>> list = new ArrayList<>();
        // 遍历表格内容
        while (rs.next()) {
            //显示列,表格的表头
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i <= columns; i++) {
                String key = m.getColumnName(i);
                String value = rs.getString(i);
                map.put(key, value);
            }
            list.add(map);
        }
        return list;
    }

    // // 执行查询
    // logger.info(" 实例化Statement对...");
    // stmt = conn.createStatement();
    // String sql;
    // sql = "select * from information_schema .columns where table_name='" + tablename + "' ";
    // ResultSet rs = stmt.executeQuery(sql);
    //
    // // 展开结果集数据库
    // while (rs.next()) {
    //     // 通过字段检索
    //     int id = rs.getInt("id");
    //     String name = rs.getString("name");
    //     String url = rs.getString("url");
    //
    // }
    // // 完成后关闭
    // rs.close();
    // stmt.close();
    // conn.close();

}

