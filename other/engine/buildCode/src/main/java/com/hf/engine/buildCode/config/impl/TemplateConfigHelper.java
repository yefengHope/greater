package com.hf.engine.buildCode.config.impl;

import com.alibaba.fastjson.JSONObject;
import com.hf.engine.buildCode.config.ITemplateConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 模板配置类解析结果获取
 * Created by HF on 2017/11/6.
 */
public class TemplateConfigHelper implements ITemplateConfig {


    @Override
    public List<String> getTemplateModule(JSONObject config,String templateModuleMatchKey) {
        List<String> templateModule = new ArrayList<>();
        Set<String> keyNames = config.keySet();
        Pattern pattern = Pattern.compile(config.getString(templateModuleMatchKey));
        for (String key : keyNames) {
            if (pattern.matcher(key).find()) {
                templateModule.add(config.getString(key));
            }
        }
        return templateModule;
    }

    @Override
    public String getTemplateModuleMatcherFtl(JSONObject config,String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return config.getString("build.ftl." + name);
    }

    @Override
    public JSONObject getBuildBlock(JSONObject config,String name) {
        JSONObject json = new JSONObject();
        Set<String> keys = config.keySet();
        for (String keyStr : keys) {
            if (keyStr.contains(name)) {
                json.put(keyStr, config.getString(keyStr));
            }
        }
        return json;
    }

    @Override
    public JSONObject getConfigType(JSONObject config,String name) {
        JSONObject map = getBuildBlock(config,name);
        return map;
    }
}
