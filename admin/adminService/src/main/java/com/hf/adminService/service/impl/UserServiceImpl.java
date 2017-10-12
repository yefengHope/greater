package com.hf.adminService.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hf.adminDao.dao.UserMapper;
import com.hf.adminDao.entity.UserEntity;
import com.hf.adminService.service.UserService;
import com.hf.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户类
 * Created by 韩峰 on 2016/8/10.
 */
@Service
public class UserServiceImpl implements UserService {

    private static Logger logger;
    static {
        logger = LoggerFactory.getLogger(UserServiceImpl.class);
    }

    @Resource
    private UserMapper userMapper;

    public UserEntity findOne(UserEntity user) throws BaseException {
        return userMapper.selectOne(user);
    }

    public List<UserEntity> findAllList(UserEntity role) {
        return userMapper.select(role);
    }

    public PageInfo<UserEntity> findAllPageList(Integer pageNumber, Integer pageSize,UserEntity userEntity) {
        if( pageNumber!= null && pageSize!= null){
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<UserEntity> users = userMapper.select(userEntity);
        return new PageInfo<>(users);
    }

    public int save(UserEntity user){
        int num = 0;
        if (user != null) {
            if (StringUtils.isBlank(user.getId())) {
                num = userMapper.insert(user);
            }
        } else {
            logger.error("保存数据，但是数据不存在");
        }
        return num;
    }

    public void update(UserEntity user) throws BaseException {
        if (user != null && StringUtils.isNotBlank(user.getId())) {
            Example example = new Example(UserEntity.class);
            example.createCriteria().andEqualTo("id", user.getId());
            userMapper.updateByExampleSelective(user, example);
        } else {
            logger.error("更新数据，但是数据不存在");
        }
    }

    public void batchUpdateState(String ids,String status) {
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr != null && idArr.length > 0 && StringUtils.isNotBlank(status)){
                userMapper.batchUpdateState(status,idArr);
            }
        }
    }
}
