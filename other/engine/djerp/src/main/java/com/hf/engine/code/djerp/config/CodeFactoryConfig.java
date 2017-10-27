package com.hf.engine.code.djerp.config;


import com.hf.common.ResourceUtils;

import java.util.Map;

/**
 * 获取代码生成器配置类
 * Created by rain on 2017/6/19.
 */
public abstract class CodeFactoryConfig {

    private static Map<String,String> configProp ;

    /**
     * classPath : 下的文件
     * @param configFileName
     */
    public static void configProp(String configFileName) {
        configProp = ResourceUtils.getResource(configFileName).getMap();
    }

    public static Map<String,String> getConfig() {
        return configProp;
    }
}
