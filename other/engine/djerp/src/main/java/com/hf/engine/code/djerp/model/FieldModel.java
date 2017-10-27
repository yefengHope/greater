package com.hf.engine.code.djerp.model;

import java.io.Serializable;

/**
 * 字段数据模型
 * Created by rain on 2017/6/13.
 */
public class FieldModel implements Serializable{

    private static final long serialVersionUID = 5172330478728751670L;

    private String dbColumName;
    /**
     * 大驼峰命名
     */
    private String upperCamelCaseName;
    /**
     * 小驼峰命名
     */
    private String lowerCamelCaseName;

    /**
     * 列长度
     */
    private String columnLength;

    /**
     * java数据类型简单名称
     */
    private String simpleDataTypeName;
    /**
     * java数据类型全路径名称
     */
    private String dataTypeName;

    /**
     * 字段注释
     */
    private String comment;

    /**
     * 是否使用方法注释
     */
    private boolean commentIsFunction;

    /**
     * 是否允许空
     */
    private boolean nullableIs;

    public String getDbColumName() {
        return dbColumName;
    }

    public void setDbColumName(String dbColumName) {
        this.dbColumName = dbColumName;
    }

    public String getUpperCamelCaseName() {
        return upperCamelCaseName;
    }

    public void setUpperCamelCaseName(String upperCamelCaseName) {
        this.upperCamelCaseName = upperCamelCaseName;
    }

    public String getLowerCamelCaseName() {
        return lowerCamelCaseName;
    }

    public void setLowerCamelCaseName(String lowerCamelCaseName) {
        this.lowerCamelCaseName = lowerCamelCaseName;
    }

    public String getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(String columnLength) {
        this.columnLength = columnLength;
    }

    public String getSimpleDataTypeName() {
        return simpleDataTypeName;
    }

    public void setSimpleDataTypeName(String simpleDataTypeName) {
        this.simpleDataTypeName = simpleDataTypeName;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isCommentIsFunction() {
        return commentIsFunction;
    }

    public void setCommentIsFunction(boolean commentIsFunction) {
        this.commentIsFunction = commentIsFunction;
    }

    public boolean isNullableIs() {
        return nullableIs;
    }

    public void setNullableIs(boolean nullableIs) {
        this.nullableIs = nullableIs;
    }

}
