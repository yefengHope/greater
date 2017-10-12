package com.hf.common.base;

import com.github.pagehelper.PageInfo;
import com.hf.common.exception.BaseException;

import java.util.List;

/**
 * Created by 韩峰 on 2016/8/9.
 */
public interface BaseService<T> {

    /**
     * 通过条件查找一个 ， = 查找
     * @param t
     * @return
     */
    T findOne(T t) throws BaseException;

    /**
     * 查询所有
     * @return List<UserEntity>
     */
    List<T> findAllList(T role) throws BaseException;

    /**
     * 查询所有
     * @param pageNumber    页码
     * @param pageSize      分页大小
     * @param t             泛型实体
     * @return  Page<UserEntity>
     */
    PageInfo<T> findAllPageList(Integer pageNumber , Integer pageSize, T t) throws BaseException;

    /**
     * 保存
     * @param t             泛型实体
     */
    int save(T t);

    /**
     * 更新
     * @param t             泛型实体
     */
    void update(T t) throws BaseException ;

    /**
     * 批量更新状态
     * @param ids       id数组
     * @param status    更新状态值
     */
    void batchUpdateState(String ids,String status) throws BaseException;
}
