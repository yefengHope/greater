package com.hf.adminService.service;


import com.hf.common.exception.BaseException;

/**
 * 用户和角色的扩展处理类
 * Created by rain on 2017/8/9.
 */
public interface UserAndRoleExtendService {

    /**
     * 批量关联用户和角色
     * @param userIds   用户id数组
     * @param roleIds   角色id数组
     */
    void batchRelateUserAndRole(String[] userIds,String[] roleIds) throws BaseException;


}
