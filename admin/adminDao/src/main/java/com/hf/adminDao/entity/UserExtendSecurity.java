package com.hf.adminDao.entity;

import com.hf.common.base.ISessionUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * 继承spring security用户
 * Created by HF on 2017/7/28.
 */
public class UserExtendSecurity extends org.springframework.security.core.userdetails.User implements ISessionUser {

    private static final long serialVersionUID = -2900369143381657475L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户帐号
     */
    private String loginNum;

    /**
     * 菜单列表
     */
    private List<SystemMenuEntity> menuList;

    /**
     * 角色访问url
     */
    private List<String> roleAccessUrlList;

    public UserExtendSecurity(String username, String password, Collection<? extends GrantedAuthority> authorities
            , String userId, String userName, String loginNum, List<SystemMenuEntity> menuList
            , List<String> roleAccessUrlList) {
        super(username, password, authorities);
        this.userId = userId;
        this.userName = userName;
        this.loginNum = loginNum;
        this.menuList = menuList;
        this.roleAccessUrlList = roleAccessUrlList;
    }

    public UserExtendSecurity(String username, String password, boolean enabled, boolean accountNonExpired
            , boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities
            , String userId, String userName, String loginNum
            , List<SystemMenuEntity> menuList, List<String> roleAccessUrlList) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.userName = userName;
        this.loginNum = loginNum;
        this.menuList = menuList;
        this.roleAccessUrlList = roleAccessUrlList;
    }

    /**
     * 用户id
     */
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 用户昵称
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 用户帐号
     */
    public String getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(String loginNum) {
        this.loginNum = loginNum;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<String> getRoleAccessUrlList() {
        return roleAccessUrlList;
    }

    public void setRoleAccessUrlList(List<String> roleAccessUrlList) {
        this.roleAccessUrlList = roleAccessUrlList;
    }

    public List<SystemMenuEntity> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SystemMenuEntity> menuList) {
        this.menuList = menuList;
    }
}
