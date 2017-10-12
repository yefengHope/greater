package com.hf.adminService.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hf.adminDao.dao.UserAndRoleMapper;
import com.hf.adminDao.entity.SystemSettingEntity;
import com.hf.adminDao.entity.UserAndRoleEntity;
import com.hf.adminService.service.UserAndRoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rain on 2017/8/9.
 */
@Service
public class UserAndRoleServiceImpl implements UserAndRoleService {

    private static org.slf4j.Logger logger;

    static {
        logger = LoggerFactory.getLogger(UserAndRoleServiceImpl.class);
    }

    @Resource
    private UserAndRoleMapper userAndRoleMapper;

    /**
     * 通过条件查找一个 ， = 查找
     *
     * @param userAndRoleEntity     用户角色关联实体
     * @return {UserAndRoleEntity}
     */
    @Override
    public UserAndRoleEntity findOne(UserAndRoleEntity userAndRoleEntity) {
        return userAndRoleMapper.selectOne(userAndRoleEntity);
    }

    /**
     * 查询所有
     *
     * @param userAndRoleEntity userAndRoleEntity     用户角色关联实体
     * @return List<UserAndRoleEntity>
     */
    @Override
    public List<UserAndRoleEntity> findAllList(UserAndRoleEntity userAndRoleEntity) {
        return userAndRoleMapper.select(userAndRoleEntity);
    }

    /**
     * 查询所有
     *
     * @param pageNumber        页码
     * @param pageSize          分页大小
     * @param userAndRoleEntity 泛型实体
     * @return Page<UserEntity>
     */
    @Override
    public PageInfo<UserAndRoleEntity> findAllPageList(Integer pageNumber, Integer pageSize, UserAndRoleEntity userAndRoleEntity) {
        if( pageNumber!= null && pageSize!= null){
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<UserAndRoleEntity> list = userAndRoleMapper.select(userAndRoleEntity);
        return new PageInfo<>(list);
    }

    /**
     * 保存
     *
     * @param userAndRoleEntity 泛型实体
     */
    @Override
    public int save(UserAndRoleEntity userAndRoleEntity) {
        int num = 0;
        if (userAndRoleEntity != null) {
            num = userAndRoleMapper.insert(userAndRoleEntity);
        }
        return num;
    }

    /**
     * 更新
     *
     * @param userAndRoleEntity 泛型实体
     */
    @Override
    public void update(UserAndRoleEntity userAndRoleEntity) {
        if (userAndRoleEntity != null && null != userAndRoleEntity.getId()) {
            Example example = new Example(SystemSettingEntity.class);
            example.createCriteria().andEqualTo("id", userAndRoleEntity.getId());
            userAndRoleMapper.updateByExampleSelective(userAndRoleEntity,example);
        } else {
            logger.error("更新为空，或者无id");
        }
    }

    /**
     * 批量更新状态
     *
     * @param ids    id数组
     * @param status 更新状态值
     */
    @Override
    @Transactional
    public void batchUpdateState(String ids, String status) {
        if (StringUtils.isNotBlank(ids) && StringUtils.isNotBlank(status)) {
            String[] idArr = ids.split(",");
            userAndRoleMapper.batchUpdateState(status,idArr);
        } else {
            logger.error("【批量更新状态】异常，无ids或者无status");
        }
    }
}
