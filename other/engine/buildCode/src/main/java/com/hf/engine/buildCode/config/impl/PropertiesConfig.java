package com.hf.engine.buildCode.config.impl;

import com.alibaba.fastjson.JSONObject;
import com.hf.common.ResourceUtils;
import com.hf.engine.buildCode.config.IFileConfig;

import java.util.Map;

/**
 * 获取propertis配置文件
 * Created by HF on 2017/11/6.
 */
public class PropertiesConfig implements IFileConfig {

    @Override
    public JSONObject getConfig(String configFileName) {
        JSONObject json = new JSONObject();
        Map<String,String> map = ResourceUtils.getResource(configFileName).getMap();
        json.putAll(map);
        return json;
    }
}
