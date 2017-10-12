package com.hf.adminWeb.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.List;

/**
 * spring boot 中使用fastJson
 * @deprecated 因为初衷只是为了试试能不能解决所有类Long转换成String，
 *              结果技能不熟练，而且又有其他方法WebMvcConfig中有spring自带转换器
 * Created by rain on 2017/8/10.
 */
// @Configurable
public class FastJsonConfig /*extends WebMvcConfigurerAdapter*/ {

    // @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // super.configureMessageConverters(converters);

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        com.alibaba.fastjson.support.config.FastJsonConfig fastJsonConfig = new com.alibaba.fastjson.support.config.FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(fastConverter);
    }
}
