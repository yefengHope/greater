
package com.hf.adminService.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hf.adminDao.dao.SystemSettingMapper;
import com.hf.adminDao.entity.SystemSettingEntity;
import com.hf.adminService.service.SystemSettingService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* 自动生成代码
* Created by HF on 2017-8-8 18:22:14.
*/
@Service("systemSettingServiceImpl")
public class SystemSettingServiceImpl implements SystemSettingService {
    private static Logger logger;
    static {
        logger = LoggerFactory.getLogger(SystemSettingServiceImpl.class);
    }

    @Resource
    private SystemSettingMapper systemSettingMapper;

    @Override
    public SystemSettingEntity findOne(SystemSettingEntity systemSettingEntity) {
        return systemSettingMapper.selectOne(systemSettingEntity);
    }

    @Override
    public List<SystemSettingEntity> findAllList(SystemSettingEntity systemSettingEntity) {
        return systemSettingMapper.select(systemSettingEntity);
    }

    @Override
    public PageInfo<SystemSettingEntity> findAllPageList(Integer pageNumber, Integer pageSize, SystemSettingEntity systemSettingEntity) {
        if( pageNumber!= null && pageSize!= null){
            PageHelper.startPage(pageNumber, pageSize);
        }
        // List<SystemSettingEntity> list = systemSettingMapper.select(systemSettingEntity);
        String keyType = systemSettingEntity.getKeyType();
        String keyName = systemSettingEntity.getKeyName();
        String keyValue = systemSettingEntity.getKeyValue();
        String keyRemark = systemSettingEntity.getKeyRemark();
        keyType = StringUtils.isNotBlank(keyType)? ("%" + keyType + "%") : null;
        keyName = StringUtils.isNotBlank(keyName)? ("%" + keyName + "%") : null;
        keyValue = StringUtils.isNotBlank(keyValue)? ("%" + keyValue + "%") : null;
        keyRemark = StringUtils.isNotBlank(keyRemark)? ("%" + keyRemark + "%") : null;

        Example example = new Example(SystemSettingEntity.class);
        example.createCriteria()
                .andLike("keyType",keyType)
                .andLike("keyName",keyName)
                .andLike("keyValue",keyValue)
                .andEqualTo("keyRemark",keyRemark)
        ;
        List<SystemSettingEntity> list = systemSettingMapper.selectByExample(example);
        return new PageInfo<SystemSettingEntity> (list);
    }

    @Override
    public int save(SystemSettingEntity systemSettingEntity) {
        int num = 0;
        if (systemSettingEntity != null) {
            num = systemSettingMapper.insert(systemSettingEntity);
        } else {
            logger.error("保存数据，但是数据不存在");
        }
        return num;
    }

    @Override
    public void update(SystemSettingEntity systemSettingEntity) {
        if (systemSettingEntity != null && null != systemSettingEntity.getId()) {
            Example example = new Example(SystemSettingEntity.class);
            example.createCriteria().andEqualTo("id", systemSettingEntity.getId());
            systemSettingMapper.updateByExampleSelective(systemSettingEntity, example);
        } else {
            logger.error("更新数据，但是数据不存在");
        }
    }

    @Override
    public void batchUpdateState(String ids,String status) {
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr != null && idArr.length > 0 && StringUtils.isNotBlank(status)){
                systemSettingMapper.batchUpdateState(status,idArr);
            }
        }
    }
}