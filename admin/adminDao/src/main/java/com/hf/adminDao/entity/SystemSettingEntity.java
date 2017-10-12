package com.hf.adminDao.entity;


import com.hf.common.base.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 自动生成代码      
 * 角色实体        
 * @author HF
 * @date 2017-8-8 18:22:13            
 */
@Entity
@Table(name = "ts_setting")
public class SystemSettingEntity extends BaseIdEntity {


    private static final long serialVersionUID = 2220518709993108074L;
    /**
     * 键类型
     */
    @Column(name = "KEY_TYPE")
    private String keyType ;
    /**
     * 键名
     */
    @Column(name = "KEY_NAME")
    private String keyName ;
    /**
     * 键值
     */
    @Column(name = "KEY_VALUE")
    private String keyValue ;
    /**
     * 键备注
     */
    @Column(name = "KEY_REMARK")
    private String keyRemark ;

    /**
     * 获取键类型
     */
    public String getKeyType () {
        return this.keyType;
    }

    /**
     * 设置键类型
     */
    public void setKeyType (String keyType) {
        this.keyType = keyType;
    }
    /**
     * 获取键名
     */
    public String getKeyName () {
        return this.keyName;
    }

    /**
     * 设置键名
     */
    public void setKeyName (String keyName) {
        this.keyName = keyName;
    }
    /**
     * 获取键值
     */
    public String getKeyValue () {
        return this.keyValue;
    }

    /**
     * 设置键值
     */
    public void setKeyValue (String keyValue) {
        this.keyValue = keyValue;
    }
    /**
     * 获取键备注
     */
    public String getKeyRemark () {
        return this.keyRemark;
    }

    /**
     * 设置键备注
     */
    public void setKeyRemark (String keyRemark) {
        this.keyRemark = keyRemark;
    }
}

