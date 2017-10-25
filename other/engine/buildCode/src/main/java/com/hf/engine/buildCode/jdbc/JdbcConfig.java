package com.hf.engine.buildCode.jdbc;


import com.hf.engine.buildCode.config.CodeFactoryConfig;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>@Title JDBC配置类 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/4/20 11:20 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class JdbcConfig implements Serializable{

    private static final long serialVersionUID = 2540371774626282307L;

    // JDBC 驱动名及数据库 URL
    private static String jdbcDriver = "";

    private static String dbUrl = "";

    // 数据库的用户名与密码，需要根据自己的设置
    private static String user = "";

    private static String pass = "";

    // 数据库表名
    private static String tableName = "";

    public static String getJdbcDriver() {
        return jdbcDriver;
    }

    public static void setJdbcDriver(String jdbcDriver) {
        JdbcConfig.jdbcDriver = jdbcDriver;
    }

    public static String getDbUrl() {
        return dbUrl;
    }

    public static void setDbUrl(String dbUrl) {
        JdbcConfig.dbUrl = dbUrl;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        JdbcConfig.user = user;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        JdbcConfig.pass = pass;
    }

    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String tableName) {
        JdbcConfig.tableName = tableName;
    }

    public static void main(String[] args) {
        System.out.println();
    }

    // 只能放在这里
    static {
        Map<String, String> resourceMap = CodeFactoryConfig.getConfig();
        JdbcConfig.setDbUrl(resourceMap.get("db.dbUrl"));
        JdbcConfig.setJdbcDriver(resourceMap.get("db.jdbcDriver"));
        JdbcConfig.setUser(resourceMap.get("db.user"));
        JdbcConfig.setPass(resourceMap.get("db.pass"));
        JdbcConfig.setTableName(resourceMap.get("db.tableName"));
    }
}
