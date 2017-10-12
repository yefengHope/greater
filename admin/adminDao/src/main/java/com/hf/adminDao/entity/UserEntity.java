package com.hf.adminDao.entity;

import com.hf.common.annotation.Note;
import com.hf.common.base.BaseNoIdEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 用户
 * Created by 韩峰 on 2016/8/2.
 */
@Entity
@Table(name = "ts_user")
public class UserEntity extends BaseNoIdEntity {
    private static final long serialVersionUID = -6507742513591751624L;


    /*  登录说明:
            可用账号,手机号和邮箱登录
        安全:
            安全问题;
    */

    @Id
    private String id;

    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称不能为空")
    @Column(name = "name")
    @Note(name = "用户昵称")
    private String name;

    /**
     * 登录账号
     */
    @NotBlank(message = "登录账号不能为空")
    @Column(name = "login_num" ,nullable = false,unique = true)
    @Note(name = "登录账号")
    private String loginNum;

    /**
     * 登录密码
     */
    @NotEmpty(message = "登录密码不能为空")
    @Column(name = "login_pwd")
    @Note(name = "登录密码")
    private String loginPwd;

    /**
     * 账号状态
     * {0:"锁定",1:"正常"}
     */
    @Column(name = "a_status")
    @Note(name = "账号状态",defaultParam="1",paramsDes = "{0:\"锁定\",1:\"正常\"}")
    private Integer aStatus;

    /**
     * 用户手机号
     */
    @NotNull(message = "用户手机号不能为空")
    @Note(name = "用户手机号")
    private Long phone;
    /**
     * 用户邮箱
     */
    @Email
    @Note(name = "用户邮箱")
    private String email;

    /**
     * 用户头像
     */
    @Note(name = "用户头像")
    @Column(name = "head_icon")
    private String headIcon;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(String loginNum) {
        this.loginNum = loginNum;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Integer getaStatus() {
        return aStatus;
    }

    public void setaStatus(Integer aStatus) {
        this.aStatus = aStatus;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
