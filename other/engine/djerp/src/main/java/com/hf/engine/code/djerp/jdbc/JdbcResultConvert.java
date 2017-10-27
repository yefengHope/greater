package com.hf.engine.code.djerp.jdbc;


import com.alibaba.fastjson.JSONObject;
import com.hf.engine.code.djerp.model.FieldModel;
import com.hf.engine.code.djerp.model.SqlMapJavaType;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rain on 2017/6/13.
 */
public class JdbcResultConvert implements Serializable {

    private static final long serialVersionUID = -784483622461737242L;

    /**
     * 处理字符串
     */
    public static List<FieldModel> formatColumn(List<Map<String, Object>> sourceList){
        List<FieldModel> outList = new ArrayList<>();
        if (sourceList == null || sourceList.isEmpty()) {
            return outList;
        }
        for (Map<String,Object> sourceMap : sourceList) {
            if (sourceMap.isEmpty()){
                continue;
            }
            JSONObject sourceJm = new JSONObject(sourceMap);
            String unformatName = sourceJm.getString("COLUMN_NAME");
            // TODO: 2017/6/13 还没有写入数据长度 
            String columnType = sourceJm.getString("COLUMN_TYPE");
            String columnDataType = sourceJm.getString("DATA_TYPE");
            String columnComment = sourceJm.getString("COLUMN_COMMENT");
            String isNullable = sourceJm.getString("IS_NULLABLE");

            String[] unformatNames = unformatName.split("_");
            StringBuilder sb = new StringBuilder();
            for (String unName : unformatNames) {
                if (StringUtils.isBlank(unName)) {
                    continue;
                }
                sb.append(StringUtils.capitalize(unName.toLowerCase()));
            }

                    String dataTypeClassName = SqlMapJavaType.getAsKey(columnDataType.toUpperCase());
            if (!StringUtils.isBlank(dataTypeClassName)) {
                try {
                    FieldModel fieldModel = new FieldModel();
                    Class classType = Class.forName(dataTypeClassName);
                    fieldModel.setDbColumName(unformatName);
                    fieldModel.setSimpleDataTypeName(classType.getSimpleName());
                    fieldModel.setDataTypeName(classType.getName());
                    String capitalizeName = sb.toString();
                    fieldModel.setLowerCamelCaseName(StringUtils.uncapitalize(capitalizeName));
                    fieldModel.setUpperCamelCaseName(capitalizeName);
                    fieldModel.setComment(columnComment);
                    fieldModel.setNullableIs(!"NO".equals(isNullable));
                    outList.add(fieldModel);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return outList;
    }
}
