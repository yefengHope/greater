package com.hf.engine.buildCode.config;

import com.alibaba.fastjson.JSONObject;

/**
 * 配置文件
 * Created by HF on 2017/11/6.
 */
public interface IFileConfig {

    JSONObject getConfig(String configFileName);
}
