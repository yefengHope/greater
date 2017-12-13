package com.hf.common.compare;

import com.hf.common.annotation.Note ;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 实体比较器
 *
 * @see Note
 * ps : 比较器要配合{@code cn.hlhdj.duoji.erp.commons.annotation.Note}的isOverlook字段使用
 *
 * Created by 韩峰 on 2017/12/13.
 */
public class BeanCompare {

    Logger logger = LoggerFactory.getLogger(BeanCompare.class);

    /**
     * 判断是否是同一个bean
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isEqualsBeanType(Object source, Object target) {
        if (source == target) {
            return true;
        }
        return source.getClass().equals(target.getClass());
    }

    /**
     * 比较两个对象的所有字段值是否相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isEqualsFullField(Object source, Object target) {
        if (source == target) {
            return true;
        }
        if (!isEqualsBeanType(source, target)) {
            return false;
        }
        Class s = source.getClass();
        Class t = target.getClass();

        Field[] fields = s.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object sourceVal = field.get(source);

                Field targetField = t.getDeclaredField(field.getName());
                targetField.setAccessible(true);

                Object targetVal = targetField.get(target);
                if (sourceVal == null && targetVal == null) {
                } else if (sourceVal != null && targetVal != null) {
                    // 如果都存在值，则判断
                    if (!sourceVal.equals(targetVal)) {
                        // 值不同，返回false
                        return false;
                    }
                } else {
                    // 任意一个值为null ，返回失败
                    return false;
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 比较两个对象的部分字段，判断字段值是否相等,并返回相对source的的字段值
     *
     * @param source
     * @param target
     * @return
     * <p>{
     * <p>     isPass         ： 是否通过
     * <p>     isNotException ： 是否不是异常
     * <p>     object         ： 差异结果
     * <p>}
     */
    public static JSONObject isEqualsFullFieldAndReturnAddVal(Object source, Object target) {
        if (source == target) {
            return returnMap(true,true,null);
        }
        if (!isEqualsBeanType(source, target)) {
            return returnMap(false,false,null);
        }

        boolean is = true;
        List<String> jsonResults = new ArrayList<>();

        Class s = source.getClass();
        Class t = target.getClass();

        Field[] fields = s.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object sourceVal = field.get(source);

                Field targetField = t.getDeclaredField(field.getName());
                targetField.setAccessible(true);

                Object targetVal = targetField.get(target);

                if (sourceVal == null && targetVal == null) {

                } else if (sourceVal != null && targetVal != null) {
                    // 如果都存在值，则判断
                    if (!sourceVal.equals(targetVal)) {
                        // 值不同，返回false
                        is = false;
                        differenceStr(jsonResults,field,sourceVal,targetVal);
                    }
                } else {
                    // 任意一个值为null ，返回失败
                    is = false;
                    differenceStr(jsonResults,field,sourceVal,targetVal);
                }

            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        if (is) {
            // 与 Source的值 全部相等
            return returnMap(true,true,null);
        } else {
            // 与 Source的值 不相等
            return returnMap(false,true, StringUtils.join(jsonResults,"\t"));
        }
    }

    /**
     * 比较两个对象的部分字段，以左边字段为判断标准，判断字段值值是否相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isEqulsLeftFeild(Object source, Object target) {
        if (source == target) {
            return true;
        }
        if (!isEqualsBeanType(source, target)) {
            return false;
        }
        Class s = source.getClass();
        Class t = target.getClass();

        Field[] fields = s.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object sourceVal = field.get(source);
                if (sourceVal == null) {
                    continue;
                }
                Field targetField = t.getDeclaredField(field.getName());
                targetField.setAccessible(true);

                Object targetVal = targetField.get(target);
                if (!sourceVal.equals(targetVal)) {
                    return false;
                }

            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 比较两个对象的部分字段，以左边字段为判断标准，判断字段值值是否相等,并返回相对source新增的的字段值
     *
     * @param source
     * @param target
     * @return
     * <p>{
     * <p>     isPass         ： 是否通过
     * <p>     isNotException ： 是否不是异常
     * <p>     object         ： 差异结果
     * <p>}
     */
    public static JSONObject isEqualsLeftFieldAndReturnAddVal(Object source, Object target) {
        if (source == target) {
            return returnMap(true,true,null);
        }
        boolean is = true;
        List<String> jsonResults = new ArrayList<>();
        if (!isEqualsBeanType(source, target)) {
            return returnMap(false,false,null);
        }
        Class s = source.getClass();
        Class t = target.getClass();

        Field[] fields = s.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object sourceVal = field.get(source);
                if (sourceVal == null) {
                    continue;
                }
                Field targetField = t.getDeclaredField(field.getName());
                targetField.setAccessible(true);

                Object targetVal = targetField.get(target);
                if (!sourceVal.equals(targetVal)) {
                    is = false;
                    differenceStr(jsonResults,field,sourceVal,targetVal);
                }

            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        if (is) {
            // 与 Source的值 全部相等
            return returnMap(true,true,null);
        } else {
            // 与 Source的值 不相等
            return returnMap(false,true, StringUtils.join(jsonResults,"\t"));
        }
    }


    public static boolean isEqualsBean(Object source, Object target) {
        return isEqualsFullField(source, target);
    }

    /**
     * 判断两个对象是否相同，并且返回对比内容
     * @param source
     * @param target
     * @return
     * <p>{
     * <p>     isPass         ： 是否通过
     * <p>     isNotException ： 是否不是异常
     * <p>     object         ： 差异结果
     * <p>}
     */
    public static JSONObject isEqualsBeanAndReturnDiffVal(Object source, Object target) {
        return isEqualsFullFieldAndReturnAddVal(source,target);
    }


    private static String differenceStr(List list,Field field,Object oldVal,Object newVal) {

        if ("serialVersionUID".equals(field.getName())) {
            return null;
        }
        StringBuilder json = new StringBuilder();
        Note note = field.getAnnotation(Note.class);

        if (note.isOverlook()) {
            // 判断该字段是否忽略
            return null;
        }

        json.append(note != null?note.name() : "@note is not empty");
        json.append("：");
        json.append("由:");
        json.append(oldVal);
        json.append("改为:");
        json.append(newVal);
        json.append("");
        list.add(json);
        return null;
    }

    private static JSONObject differenceJson(Field field, Object oldVal, Object newVal) {
        JSONObject json = new JSONObject();
        Note note = field.getAnnotation(Note.class);
        json.put("name",note.name());
        json.put("oldVal",oldVal);
        json.put("newVal",newVal);
        return json;
    }

    /**
     * 返回的map结果集
     * @param isPass            是否通过
     * @param isNotException    是否不是异常
     * @param object            差异结果
     * @return JSONObject
     * <p>{
     * <p>     isPass         ： 是否通过
     * <p>     isNotException ： 是否不是异常
     * <p>     object         ： 差异结果
     * <p>}
     */
    private static JSONObject returnMap (boolean isPass, boolean isNotException, Object object) {
        JSONObject json = new JSONObject();
        json.put("isPass",isPass);
        json.put("isNotException",isNotException);
        json.put("difference",object);
        return json;
    }

}
