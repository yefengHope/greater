package com.hf.adminDao.dao;

import com.hf.adminDao.entity.UserAndRoleEntity;
import com.hf.common.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by HanFeng on 2017/7/26.
 */
@Mapper
public interface UserAndRoleMapper extends BaseMapper<UserAndRoleEntity> {

    void batchUpdateState(@Param("status") String status, @Param("ids") String [] ids);
}
