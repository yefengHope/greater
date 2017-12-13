package com.hf.common.annotation;

/**
 * 对类、字段和方法的说明
 * 在代码生成器中使用次注解
 * Created by rain on 2016/11/24.
 */
public @interface Note {
    /**
     * 参数名称(中文名)
     * @return
     */
    String name();

    boolean isOverlook() default false;

    /**
     * 默认参数
     * {@code BeanCompare}类校验时，是否忽略
     * @return
     */
    String defaultParam() default "";

    /**
     * 参数说明(备注)
     * @return
     */
    String paramsDes() default "";

    /**
     * 说明
     * @return
     */
    String description() default "";
}
