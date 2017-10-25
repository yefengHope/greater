package com.hf.engine.buildCode.jdbc;

import java.util.List;
import java.util.Map;

/**
 * <p>@Title 类标题描述 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/4/20 16:34 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public interface JdbcResult {

    /**
     * 根据sql获取内容
     * @param sql
     * @return
     */
    List<Map<String, Object>> getList(String sql);

    /**
     * <p>获取数据表结构
     * <p>表名称为AbstractJdbcConfig里的名称
     * <p>@return
     * <p>{
     * <p>     "COLUMN_COMMENT":"列注释",
     * <p>     "COLUMN_KEY":"列键 (如:PRI)",
     * <p>     "COLUMN_NAME":"列名称",
     * <p>     "COLUMN_TYPE":"列类型 (如:bigint(20))",
     * <p>     "DATA_TYPE":"数据类型 (如: bigint)",
     * <p>     "EXTRA":"auto_increment",
     * <p>     "GENERATION_EXPRESSION":"",
     * <p>     "IS_NULLABLE":"是否允许null (如:NO)",
     * <p>     "NUMERIC_PRECISION":"数字精度",
     * <p>     "NUMERIC_SCALE":"0",
     * <p>     "ORDINAL_POSITION":"顺序位置 (如:1)",
     * <p>     "PRIVILEGES":"select,insert,update,references",
     * <p>     "TABLE_CATALOG":"def",
     * <p>     "TABLE_NAME":"ts_user",
     * <p>     "TABLE_SCHEMA":"chihuo"
     * <p>}
     */
    List<Map<String, Object>> getTableStructureList();

}
