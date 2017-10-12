package com.hf.adminService.service;



import com.hf.adminDao.entity.RoleEntity;
import com.hf.common.base.BaseService;

import java.util.List;

/**
 * <p>@Title 类标题描述 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author HF</p>
 * <p>@date 2017/7/3 15:50 创建日期</p>
 */
public interface RoleService extends BaseService<RoleEntity> {

    List<RoleEntity> getRoles(String userId);

    /**
     * 实现spring security 认证方法
     * @param loginId       用户id
     * @param domainStr     security domain
     * @param permissionStr security permission
     * @return boolean
     */
    boolean authorized(String loginId,String domainStr,String permissionStr);
}
