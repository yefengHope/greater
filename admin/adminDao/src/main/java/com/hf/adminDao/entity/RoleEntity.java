package com.hf.adminDao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.hf.common.base.BaseIdEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 角色实体
 *
 * @author hanfeng
 * @date 2017-7-26 8:29:54
 */
@Entity
@Table(name = "ts_role")
public class RoleEntity extends BaseIdEntity {
    private static final long serialVersionUID = 4292158331522374673L;
    /**
     * 业务系统编号
     */
    @Column(name = "SYSTEM_ID")
    private String systemId;
    /**
     * 角色名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 角色唯一字符串
     */
    @Column(name = "NAME_KEY")
    private String nameKey;
    /**
     * 角色有效期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "VALIDITY")
    private Date validity;
    /**
     * 排序序号,默认为1
     */
    @Column(name = "SORT")
    private Integer sort;
    /**
     * 菜单访问权限
     */
    @Column(name = "ARIGHTS")
    private String arights;
    /**
     * 菜单操作权限
     */
    @Column(name = "HRIGHTS")
    private String hrights;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * 获取角色名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    /**
     * 获取角色有效期
     */
    public Date getValidity() {
        return this.validity;
    }

    /**
     * 设置角色有效期
     */
    public void setValidity(Date validity) {
        this.validity = validity;
    }


    /**
     * 获取排序序号,默认为1
     */
    public Integer getSort() {
        return this.sort;
    }

    /**
     * 设置排序序号,默认为1
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取菜单访问权限
     */
    public String getArights() {
        return this.arights;
    }

    /**
     * 设置菜单访问权限
     */
    public void setArights(String arights) {
        this.arights = arights;
    }

    /**
     * 获取菜单操作权限
     */
    public String getHrights() {
        return this.hrights;
    }

    /**
     * 设置菜单操作权限
     */
    public void setHrights(String hrights) {
        this.hrights = hrights;
    }

}

