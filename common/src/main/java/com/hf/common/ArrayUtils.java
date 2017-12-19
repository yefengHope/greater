package com.hf.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集合工具类
 */
public class ArrayUtils {

    /**
     * List集合转换成map
     *
     * @param list list
     * @param key  作为key的类的属性
     * @return {@code Map<String,Object>}
     * @throws Exception
     */
    public static Map listToMap(List<?> list, String key) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (list == null || list.size() <= 0) {
            return map;
        }
        Object obj0 = list.get(0);
        Field fieldKey = obj0.getClass().getDeclaredField(key);
        fieldKey.setAccessible(true);

        for (Object obj : list) {
            Object objKey = fieldKey.get(obj);
            map.put(String.valueOf(objKey), obj);
        }
        return map;
    }

}
