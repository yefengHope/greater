package com.hf.engine.buildCode.jdbc;


import com.alibaba.fastjson.JSONObject;
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

    private static final long serialVersionUID = 9126324476724660897L;
    // JDBC 驱动名及数据库 URL
    private String jdbcDriver = "";

    private String dbUrl = "";

    // 数据库的用户名与密码，需要根据自己的设置
    private String user = "";

    private String pass = "";

    // 数据库表名
    private String tableName = "";

    public JdbcConfig(JSONObject resourceMap) {
         this.dbUrl = resourceMap.getString("db.dbUrl");
        this.jdbcDriver = resourceMap.getString("db.jdbcDriver");
        this.user = resourceMap.getString("db.user");
        this.pass = resourceMap.getString("db.pass");
        this.tableName = resourceMap.getString("db.tableName");
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
