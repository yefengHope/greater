package com.hf.common.dataUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>@Title 类标题描述 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@author hanfeng</p>
 * <p>@date 2016/11/18 15:32 创建日期</p>
 */
public class ListUtils {

    /**
     * 泛型反射获取entity信息
     * <p>获取实体的字段,字段类型,字段上的注解</p>
     * @param beanName 完整类名"com.fengyu.system.domain.User"
     * @return  {List}
     */
    public static List getClassInfo (String beanName) {
        List<Map> list = new ArrayList<>();
        try {
            //得到对象
            Class aClass = Class.forName(beanName);
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                Map<String,Object > map = new HashMap<>();
                String fieldName = field.getName();    //字段名
                Class<?> fieldType = field.getType();    //字段类型
                String fieldTypeStr = fieldType.getName(); //字段类型
                Annotation[] annotations = field.getAnnotations();  //字段上的注解
//                System.out.println(fieldName);
//                for (Annotation annotation : annotations) {
//                    System.out.print(annotation.annotationType().getCanonicalName() + "\t");
//                    System.out.print(annotation.toString() + "\t");
//                    System.out.println();
//                }
                map.put("fieldName",fieldName);
                map.put("fieldType",fieldTypeStr);
                map.put("annotations",annotations);
                list.add(map);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *  List对象转换成Map
     * <p>@Description 通过传入的对象field名,和传入get方法名,反射获取</p>
     * <p>author hanfeng</p>
     * <p>@Date 2016/11/18 17:00</p>
     * <p>@param tClass     对象.Class</p>
     * <p>@param list       被转换的对象List</p>
     * <p>@param key        对象中作为key的字段</p>
     * <p>@param methodName 对象中 key字段的获取方法名</p>
     * <p>@return  java.util.Map </p>
     */
    public static <T> Map listToMap(Class<T> tClass,List<T> list, String key,String methodName ) {
        Map<String,T> map = new HashMap<>();
        key  = key != null ? key.trim() : "";
        methodName  = methodName != null ? methodName.trim() : "";

        try {
            Class aClass = tClass.getClass();
            aClass = aClass.getClass();//获取运行时对象
            Class<?> fieldType = aClass.getDeclaredField(key).getType();
//            Class<?> fieldType = aClass.getField(key).getType();
            Method method =  aClass.getDeclaredMethod(methodName,fieldType);
            for (T obj : list) {
                String id = String.valueOf(method.invoke(obj,null));
                map.put(id,obj);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查找实体中某个字段对应的方法
     * @param beanName      实体名字
     * @param fieldName     字段名
     * @param methodName    字段对应的方法(如:get/set)
     * @return {Method}
     */
    public static Method getFieldMethodForClass(String beanName,String fieldName,String methodName) {
        Method method = null;
        try {
        Class aClass;
            aClass = Class.forName(beanName);
            Class<?> fieldType = aClass.getDeclaredField(fieldName).getType();
            method =  aClass.getDeclaredMethod(methodName,fieldType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return method;
    }


    public static void main(String[] args) {
        getClassInfo("com.fengyu.system.domain.User");
    }
}
