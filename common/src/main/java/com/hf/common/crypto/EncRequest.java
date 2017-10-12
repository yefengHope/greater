package com.hf.common.crypto;

import java.lang.annotation.*;

/**
 * <p>@Title 方法参数解密注解 </p>
 * <p>@Description 方法参数解密注解</p>
 * <p>@author hanfeng</p>
 * <p>@date 2016/11/30 10:55 创建日期</p>
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncRequest {
}
