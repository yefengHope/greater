package com.hf.adminWeb.security;

import com.hf.adminDao.entity.SystemMenuEntity;
import com.hf.adminDao.entity.UserEntity;
import com.hf.adminDao.entity.UserExtendSecurity;
import com.hf.adminService.service.LoginService;
import com.hf.adminService.service.RoleService;
import com.hf.adminService.service.SystemMenuService;
import com.hf.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>@Title spring security 实现自定义验证 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/7/3 15:43 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private LoginService loginService;

    @Resource
    private RoleService roleService;

    @Resource
    private SystemMenuService systemMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("用户名为空");
        }

        UserEntity login = null;
        try {
            login = loginService.findByUsername(username);
        } catch (BaseException e) {
            e.printStackTrace();
        }
        UserExtendSecurity user;
        boolean isEnabled = true;               //是否可用
        boolean isAccountNonExpired = true;     //是否过期
        boolean isCredentialsNonExpired = true; //证书不过期为true
        boolean isAccountNonLocked = true;      //账户未锁定为true

        if (login != null) {
            // 查询有此用户
            Set<GrantedAuthority> authorities = new HashSet<>();
            List<String> accessMenuIds = new ArrayList<>();
            roleService.getRoles(login.getId())
                    .forEach(r -> {
                                authorities.add(new SimpleGrantedAuthority(r.getNameKey()));
                                String arigth = r.getArights();
                                if (StringUtils.isNotBlank(arigth)) {
                                accessMenuIds.add(arigth);
                                }
                            }
                    );
            // TODO: 2017/8/14 用户有效性判断
            String accessMenuIdStr = StringUtils.join(accessMenuIds,",");
            List<SystemMenuEntity> systemMenuEntities = systemMenuService.selectByIds(accessMenuIdStr);

            List<String> accessUrlIds = new ArrayList<>();
            List<SystemMenuEntity> systemMenuCache = new ArrayList<>();
            systemMenuEntities.forEach(r -> {
                if (1 == r.getStatus()){
                    accessUrlIds.add(r.getMenuAddress());
                    systemMenuCache.add(r);
                }
            });

            user = new UserExtendSecurity(
                    login.getId(),
                    login.getLoginPwd(),
                    isEnabled,               //是否可用
                    isAccountNonExpired,       //是否过期
                    isCredentialsNonExpired,   //证书不过期为true
                    isAccountNonLocked,       //账户未锁定为true
                    authorities,
                    login.getId(),
                    login.getName(),
                    login.getLoginNum(),
                    systemMenuCache,
                    accessUrlIds
            );
        } else {
            // 用户不可用
            isEnabled = false;
            user = new UserExtendSecurity(
                    null,
                    null,
                    isEnabled,               //是否可用
                    isAccountNonExpired,       //是否过期
                    isCredentialsNonExpired,   //证书不过期为true
                    isAccountNonLocked,       //账户未锁定为true
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }

        return user;
    }
}
