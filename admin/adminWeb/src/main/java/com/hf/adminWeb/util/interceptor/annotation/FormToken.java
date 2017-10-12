package com.hf.adminWeb.util.interceptor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>@Title 防止重复提交注解 </p>
 * <p>@Description 防止重复提交注解，用于方法上<br/>
 * 在新建页面方法上，设置needSaveToken()为true，此时拦截器会在Session中保存一个token，
 * 同时需要在新建的页面中添加
 * <input type="hidden" name="token" value="${token}">
 * <br/>
 * 保存方法需要验证重复提交的，设置needRemoveToken为true
 * 此时会在拦截器中验证是否重复提交 <p>
 * <p>@author hanfeng</p>
 * <p>@date 2016/11/3 16:19 创建日期</p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormToken {
    boolean needSaveToken() default false;
    boolean needRemoveToken() default false;
}
