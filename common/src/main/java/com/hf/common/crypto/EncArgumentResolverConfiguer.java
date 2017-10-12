package com.hf.common.crypto;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * <p>@Title 参数解密配置类 </p>
 * <p>@Description 参数解密配置类</p>
 * <p>@author hanfeng</p>
 * <p>@date 2016/11/30 10:49 创建日期</p>
 */
@Configuration
public class EncArgumentResolverConfiguer extends WebMvcConfigurerAdapter {

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new EncArgumentResolver());
    }
}
