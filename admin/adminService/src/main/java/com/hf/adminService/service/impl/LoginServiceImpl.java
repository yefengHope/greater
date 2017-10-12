package com.hf.adminService.service.impl;

import com.hf.adminDao.entity.UserEntity;
import com.hf.adminService.service.LoginService;
import com.hf.adminService.service.UserService;
import com.hf.common.exception.BaseException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>@Title 类标题描述 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/7/3 16:36 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserService userService;

    @Override
    public UserEntity findByUsername(String username) throws BaseException {
        UserEntity user = new UserEntity();
        user.setLoginNum(username);

        return  userService.findOne(user);
    }
}
