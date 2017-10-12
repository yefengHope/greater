package com.hf.adminService.service;

import org.springframework.beans.BeansException;

import java.util.List;

/**
 * spring 上下文配置
 * Created by rain on 2016/11/16.
 */
public interface SpringContextService {

    /**
     * 注意 bean name默认 = 类名(首字母小写)
     * 例如: A8sClusterDao = getBean("k8sClusterDao")
     * @param name  bean的name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;


    /**
     * 获取所有bean
     * @return {List}
     * @throws BeansException
     */
    List<Object> getBeans() throws BeansException;

    /**
     * 根据类名获取到bean
     * @param <T>
     * @param clazz
     * @return
     * @throws BeansException
     */
    <T> T getBeanByName(Class<T> clazz) throws BeansException;

    /**
     * 容器是否包含bean
     * @param name  bean的name
     * @return
     */
    boolean containsBean(String name);

    /**
     * bean是否单例
     * @param name  bean的name
     * @return
     */
    boolean isSingleton(String name);
}
