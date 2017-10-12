package com.hf.adminService.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hf.adminDao.dao.RoleMapper;
import com.hf.adminDao.dao.UserAndRoleMapper;
import com.hf.adminDao.entity.RoleEntity;
import com.hf.adminDao.entity.UserAndRoleEntity;
import com.hf.adminDao.entity.UserEntity;
import com.hf.adminService.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>@Title 类标题描述 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/7/3 16:37 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static Logger logger;
    static {
        logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    }

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserAndRoleMapper userAndRoleMapper;

    @Override
    public List<RoleEntity> getRoles(String userId) {
        UserAndRoleEntity userAndRole = new UserAndRoleEntity();
        userAndRole.setUserId(userId);
        List<UserAndRoleEntity> userAndRoleEntityList = userAndRoleMapper.select(userAndRole);
        if (userAndRoleEntityList.size() <= 0) {
            logger.error("数据表UserAndRoleEntity为空，无对应数据");
            return null;
        }
        Set<Long> roleSet = new HashSet<>();
        userAndRoleEntityList.forEach( n -> { roleSet.add(n.getRoleId()); } );
        return roleMapper.findByRoleList(new ArrayList<>(roleSet));
    }

    @Override
    public boolean authorized(String loginId, String domainStr, String permissionStr) {
        return false;
    }

    @Override
    public RoleEntity findOne(RoleEntity role) {
        return roleMapper.selectOne(role);
    }

    @Override
    public List<RoleEntity> findAllList(RoleEntity role) {
        return roleMapper.select(role);
    }

    @Override
    public PageInfo<RoleEntity> findAllPageList(Integer pageNumber, Integer pageSize, RoleEntity role) {
        if( pageNumber!= null && pageSize!= null){
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<RoleEntity> list = roleMapper.select(role);
        return new PageInfo<RoleEntity> (list);
    }

    @Override
    public int save(RoleEntity role) {
        int num = 0;
        if (role != null) {
            num = roleMapper.insert(role);
        } else {
            logger.error("保存数据，但是数据不存在");
        }
        return num;
    }
    @Override
    public void update(RoleEntity role) {
        if (role != null && null != role.getId()) {
            Example example = new Example(UserEntity.class);
            example.createCriteria().andEqualTo("id", role.getId());
            roleMapper.updateByExampleSelective(role, example);
        } else {
            logger.error("更新数据，但是数据不存在");
        }
    }

    @Override
    public void batchUpdateState(String ids,String status) {
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr != null && idArr.length > 0 && StringUtils.isNotBlank(status)){
                roleMapper.batchUpdateState(status,idArr);
            }
        }
    }
}
