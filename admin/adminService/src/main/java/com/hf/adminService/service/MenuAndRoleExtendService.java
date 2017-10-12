package com.hf.adminService.service;


import com.hf.common.exception.BaseException;

/**
 * 菜单和角色的扩展服务
 * Created by rain on 2017/8/17.
 */
public interface MenuAndRoleExtendService {

    /**
     * 批量关联菜单和角色
     * @param menuIds   菜单id字符串集 1，2，3，...
     * @param roleIds   角色id数组
     */
    void batchRelateMenuAndRole(String menuIds, String[] roleIds) throws BaseException;

    /**
     * /**
     * 批量关联菜单和角色
     * @param menuIds   菜单id字符串集 1，2，3，...
     * @param roleIds   角色id数组
     * @param status    状态设置 1新增 0从原有删除
     * @throws BaseException
     */
    void batchRelateMenuAndRole(String menuIds, String[] roleIds,int status) throws BaseException;
}
