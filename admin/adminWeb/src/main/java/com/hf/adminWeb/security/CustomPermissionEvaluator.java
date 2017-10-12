package com.hf.adminWeb.security;

import com.hf.adminDao.entity.UserEntity;
import com.hf.adminService.service.LoginService;
import com.hf.adminService.service.RoleService;
import com.hf.common.exception.BaseException;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>@Title 类标题描述 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/7/3 15:52 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Resource
    private LoginService loginService;

    @Resource
    private RoleService roleService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        String username = authentication.getName();
        UserEntity login = null;
        try {
            login = loginService.findByUsername(username);
        } catch (BaseException e) {
            e.printStackTrace();
        }
        return roleService.authorized(login.getId(), targetDomainObject.toString(), permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // not supported
        return false;
    }
}
