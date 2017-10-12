package com.hf.common.base;

import com.github.pagehelper.PageInfo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BaseController
 * Created by rain on 2017/3/15.
 */
public class BaseController {



    // =============== 数据返回封装 ===============

    /**
     * 返回Bootstrap table 规定的数据
     * 自己封装的数据返回类型，bootstrap-table要求服务器返回的json数据必须包含：total，rows两个节点
     * @param status    返回状态
     * @param info      返回提示
     * @param data      数据
     * @param url       url
     * @return
     */
    public Map returnAjax(boolean status, String info, Object data, String url) {
        Map<String ,Object> map = new HashMap<>();
        map.put("status",status);
        map.put("info",info);
        map.put("data",data);
        map.put("url",url);
        return map;
    }

    /**
     * 返回Bootstrap table 规定的数据
     * 自己封装的数据返回类型，bootstrap-table要求服务器返回的json数据必须包含：total，rows两个节点
     * @param status    返回状态
     * @param info      返回提示
     * @param rows      数据
     * @param total     一共多少条
     * @return
     */
    public Map returnBootTable(boolean status, String info, List rows, int total) {
        Map<String ,Object> map = new HashMap<>();
        map.put("status",status);
        map.put("info",info);
        map.put("total",total);
        map.put("rows",rows);
        return map;
    }

    /**
     * 返回Bootstrap table 规定的数据
     * 自己封装的数据返回类型，bootstrap-table要求服务器返回的json数据必须包含：total，rows两个节点
     * @param status    返回状态
     * @param info      返回提示
     * @return
     */
    public Map returnBootTable(boolean status, String info, PageInfo page) {
        Map<String ,Object> map = new HashMap<>();
        map.put("status",status);
        map.put("info",info);
        map.put("total",page.getTotal());
        map.put("rows",page.getList());
        return map;
    }

    /**
     * 根据类型创建每个字段为null的对象
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Object createObjFromClass(Class<T> clazz) {
        T temp = null;
        try {
            temp = clazz.newInstance();
            Field[] fields = clazz.getFields();
            for (Field field : fields) {
                field.set(temp,null);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return temp;
    }

}
