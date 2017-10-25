package com.hf.engine.buildCode.jdbc;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <p>@Title 类标题描述 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/4/20 16:39 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class JdbcResultImpl extends AbstractJdbcConnection implements JdbcResult,Serializable {

    private static final long serialVersionUID = -2924831482896464794L;

    @Override
    public List<Map<String, Object>> getList(String sql) {
        try {
            getResultSet(sql);
            return parseResultSet();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;


    }

    @Override
    public List<Map<String, Object>> getTableStructureList() {
        try {
            String sql = "select * from information_schema.columns where table_name='" + JdbcConfig.getTableName() + "' ";
            getResultSet(sql);
            return parseResultSet();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
