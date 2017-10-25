package com.hf.engine.buildCode.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库字段类型映射java类型(全路径)
 * Created by rain on 2017/6/4.
 */
public class SqlMapJavaType implements Serializable {

    private static final long serialVersionUID = -1808599074360717282L;

    private static Map<String,String> map = new HashMap<String,String>();

    static {

        map.put("VARCHAR",   "java.lang.String");
        map.put("CHAR",      "java.lang.String");
        map.put("BLOB",      "java.lang.byte[]");
        map.put("TEXT",      "java.lang.String");
        map.put("INTEGER",   "java.lang.Long");
        map.put("ID",        "java.lang.Long");
        map.put("TINYINT",   "java.lang.Integer");
        map.put("SMALLINT",  "java.lang.Integer");
        map.put("MEDIUMINT", "java.lang.Integer");
        map.put("BIT",       "java.lang.Boolean");
        map.put("BIGINT",    "java.lang.Long");
        map.put("DECIMAL",   "java.math.BigDecimal");
        map.put("FLOAT",     "java.lang.Float");
        map.put("DOUBLE",    "java.lang.Double");

        map.put("YEAR",      "java.sql.Date");
        map.put("DATE",      "java.sql.Date");
        map.put("TIME",      "java.util.Date");
        map.put("DATETIME",  "java.util.Date");
        map.put("TIMESTAMP", "java.util.Date");
        // map.put("TIME",      "java.sql.Time");
        // map.put("DATETIME",  "java.sql.Timestamp");
        // map.put("TIMESTAMP", "java.sql.Timestamp");

    }

    /**
     * 获取映射集合
     */
    public static Map<String,String> getInstance() {
        if (map.isEmpty()) {
            throw new NullPointerException("sql对应java类型集合不存在");
        }
        return map;
    }

    /**
     * 根据key ,获取值
     * @param key {String}key
     * @return    {String}value
     */
    public static String getAsKey(String key) {
        if (map.isEmpty()) {
            throw new NullPointerException("sql对应java类型集合不存在");
        }
        return map.get(key);
    }

}
