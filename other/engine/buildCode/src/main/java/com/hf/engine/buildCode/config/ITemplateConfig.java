package com.hf.engine.buildCode.config;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * 模板配置
 * Created by HF on 2017/11/6.
 */
public interface ITemplateConfig {

    /**
     * 获取配置模块
     * @param config                配置json
     * @param templateModuleMatchKey   模板模块配置名匹配表达式的key，怎么识别model，service，controller
     * @return
     */
    List<String> getTemplateModule(JSONObject config,String templateModuleMatchKey);

    /**
     * 获取配置模块名称对应的ftl文件
     *
     * @param name
     * @return
     */
    String getTemplateModuleMatcherFtl(JSONObject config,String name);


    /**
     * 从配置文件获取构建模块配置属性
     * @param name
     * @return
     */
    JSONObject getBuildBlock(JSONObject config,String name);

    /**
     * 获取模块配置内容
     *
     * @param name 预备项为: {"dao","service","service.impl","controller","mybatis","html.list","html.addOrEdit"}
     * @return {
     * build : "构建类型",
     * fileName : "构建文件名",
     * comment : "构建注释",
     * package : "构建路径",
     * }
     */
    JSONObject getConfigType(JSONObject config,String name);
}
