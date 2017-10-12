package com.hf.adminService.service;


import com.hf.adminDao.entity.UserEntity;
import com.hf.common.exception.BaseException;

/**
 * <p>@Title 类标题描述 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/7/3 15:50 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public interface LoginService {


    /**
     * 通过登录账号查询用户
     * @param username  登录账号，邮箱，手机号
     * @return  User
     */
    UserEntity findByUsername(String username) throws BaseException;
}
