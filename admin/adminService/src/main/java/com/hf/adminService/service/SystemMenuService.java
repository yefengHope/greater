package com.hf.adminService.service;


import com.hf.adminDao.entity.SystemMenuEntity;
import com.hf.common.base.BaseService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 自动生成代码
* Created by HF on 2017-8-8 22:26:54.
*/
public interface SystemMenuService extends BaseService<SystemMenuEntity> {

    List<SystemMenuEntity> selectByIds(@NotNull String ids);
}