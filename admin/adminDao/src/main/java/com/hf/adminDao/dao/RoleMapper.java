package com.hf.adminDao.dao;

import com.hf.adminDao.entity.RoleEntity;
import com.hf.common.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by HanFeng on 2017/7/26.
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {

    List<RoleEntity> findByRoleList(@Param("roles") List<Object> roles);

    void batchUpdateState(@Param("status") String status, @Param("ids") String [] ids);
}
