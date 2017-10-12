
package com.hf.adminService.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hf.adminDao.dao.SystemMenuMapper;
import com.hf.adminDao.entity.SystemMenuEntity;
import com.hf.adminService.service.SystemMenuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* 自动生成代码
* Created by HF on 2017-8-8 22:26:54.
*/
@Service("systemMenuServiceImpl")
public class SystemMenuServiceImpl implements SystemMenuService {
    private static Logger logger;
    static {
        logger = LoggerFactory.getLogger(SystemMenuServiceImpl.class);
    }

    @Resource
    private SystemMenuMapper systemMenuMapper;

    @Override
    public SystemMenuEntity findOne(SystemMenuEntity systemMenuEntity) {
        return systemMenuMapper.selectOne(systemMenuEntity);
    }

    @Override
    public List<SystemMenuEntity> findAllList(SystemMenuEntity systemMenuEntity) {
        return systemMenuMapper.select(systemMenuEntity);
    }

    @Override
    public PageInfo<SystemMenuEntity> findAllPageList(Integer pageNumber, Integer pageSize, SystemMenuEntity systemMenuEntity) {
        if( pageNumber!= null && pageSize!= null){
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<SystemMenuEntity> list = systemMenuMapper.select(systemMenuEntity);
        return new PageInfo<SystemMenuEntity> (list);
    }

    @Override
    public int save(SystemMenuEntity systemMenuEntity) {
        int num = 0;
        if (systemMenuEntity != null) {
            num = systemMenuMapper.insertUseGeneratedKeys(systemMenuEntity);
            // num = systemMenuMapper.insert(systemMenuEntity);
        } else {
            logger.error("保存数据，但是数据不存在");
        }
        return num;
    }

    @Override
    public void update(SystemMenuEntity systemMenuEntity) {
        if (systemMenuEntity != null && null != systemMenuEntity.getId()) {
            Example example = new Example(SystemMenuEntity.class);
            example.createCriteria().andEqualTo("id", systemMenuEntity.getId());
            systemMenuMapper.updateByExampleSelective(systemMenuEntity, example);
        } else {
            logger.error("更新数据，但是数据不存在");
        }
    }

    @Override
    public void batchUpdateState(String ids,String status) {
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr != null && idArr.length > 0 && StringUtils.isNotBlank(status)){
                systemMenuMapper.batchUpdateState(status,idArr);
            }
        }
    }

    @Override
    public List<SystemMenuEntity> selectByIds(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            return systemMenuMapper.selectByIds(ids);
        }else {
            return null;
        }
    }
}