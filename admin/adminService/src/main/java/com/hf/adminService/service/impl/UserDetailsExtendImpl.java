package com.hf.adminService.service.impl;

import com.hf.adminDao.entity.SystemMenuEntity;
import com.hf.adminDao.entity.UserExtendSecurity;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Created by HanFeng on 2017/7/28.
 */
public class UserDetailsExtendImpl extends UserExtendSecurity {

    private static final long serialVersionUID = 3823143151776481244L;

    public UserDetailsExtendImpl(String username, String password, Collection<? extends GrantedAuthority> authorities, String userId, String userName, String loginNum, List<SystemMenuEntity> menuList, List<String> roleAccessUrlList) {
        super(username, password, authorities, userId, userName, loginNum, menuList, roleAccessUrlList);
    }

    public UserDetailsExtendImpl(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String userId, String userName, String loginNum, List<SystemMenuEntity> menuList, List<String> roleAccessUrlList) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, userId, userName, loginNum, menuList, roleAccessUrlList);
    }
}
