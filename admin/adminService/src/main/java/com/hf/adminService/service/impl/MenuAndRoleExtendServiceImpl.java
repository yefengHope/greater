package com.hf.adminService.service.impl;

import com.hf.adminDao.entity.RoleEntity;
import com.hf.adminDao.entity.SystemMenuEntity;
import com.hf.adminService.service.MenuAndRoleExtendService;
import com.hf.adminService.service.RoleService;
import com.hf.adminService.service.SystemMenuService;
import com.hf.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by rain on 2017/8/17.
 */
@Service
public class MenuAndRoleExtendServiceImpl implements MenuAndRoleExtendService {

    @Resource
    private SystemMenuService systemMenuService;

    @Resource
    private RoleService roleService;

    /**
     * 批量关联菜单和角色
     *
     * @param menuIds 菜单id字符串集 1，2，3，...
     * @param roleIds 角色id数组
     */
    @Override
    public void batchRelateMenuAndRole(String menuIds, String[] roleIds) throws BaseException {
        batchRelateMenuAndRole(menuIds,roleIds,1);
    }

    @Override
    public void batchRelateMenuAndRole(String menuIds, String[] roleIds, int status) throws BaseException {
        if (StringUtils.isNotBlank(menuIds ) && null != roleIds
                && roleIds.length > 0) {
            Date curDate = new Date();
            List<SystemMenuEntity> menuEntityList = systemMenuService.selectByIds(menuIds);
            List<String> menuIdsPreInsertDb = new ArrayList<>(); // 准备插入数据库
            for (SystemMenuEntity menuEntity : menuEntityList){
                if (1 == menuEntity.getStatus()) {
                    menuIdsPreInsertDb.add(String.valueOf(menuEntity.getId()));
                }
            }

            for (String roleId : roleIds) {
                // 查询角色是否存在
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setId(Long.valueOf(roleId));
                roleEntity.setStatus(1); //设置查询状态
                // 查询数据库
                RoleEntity roleDb = roleService.findOne(roleEntity);

                if (null == roleDb || curDate.after(roleDb.getValidity())) {
                    continue;
                }
                // 分割访问字符串集
                String[] menuArights;
                String menuArightsIds = roleDb.getArights();
                if (StringUtils.isNotBlank(menuArightsIds)) {
                    // 如果已经存在访问权限
                    menuArights = menuArightsIds.split(",");
                } else {
                    menuArights = new String[0];
                }

                List<String> arightsList = new ArrayList<>(menuIdsPreInsertDb);
                Set<String> arightsSet = new HashSet<>();
                List<String> menuArightList = new ArrayList<>(Arrays.asList(menuArights));
                switch (status) {
                    case 0 : {
                        menuArightList.removeAll(arightsList);
                    }break;
                    case 1 : {
                        // 数据库内容加上新增菜单权限
                        menuArightList.addAll(arightsList);
                    }break;
                    default:{
                        throw new BaseException("status，不符合");
                    }
                }
                arightsSet = new HashSet<>(arightsList);
                String arightsStr = org.apache.commons.lang3.StringUtils.join(arightsSet,",");
                roleDb.setArights(arightsStr);
                roleService.update(roleDb);
            }
        } else {
            throw new NullPointerException("【批量关联用户和角色】，无userIds或roleIds");
        }
    }
}
