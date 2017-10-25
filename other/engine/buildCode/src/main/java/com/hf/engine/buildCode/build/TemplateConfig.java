package com.hf.engine.buildCode.build;

import com.alibaba.fastjson.JSON;
import com.hf.engine.buildCode.config.CodeFactoryConfig;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;

/**
 * <p>@Title 模板配置 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/6/19 15:54 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public abstract class TemplateConfig implements Serializable {

    private static final long serialVersionUID = 8304983722605215645L;

    private final static String TEMPLATE_MODULE = "template.module.[0-9]+";

    private static Map<String, String> config = CodeFactoryConfig.getConfig();

    private static String dirPath;

    private static List<String> templateModule;

    static {
        String path = config.get("template.package.path");
        if (StringUtils.isBlank(path)) {
            throw new NullPointerException("template.package.path 变量不存在");
        }
        dirPath = path;
    }

    /**
     * 获取配置模块
     */
    public synchronized static List<String> getTemplateModule() {
        if (templateModule == null) {
            templateModule = new ArrayList<>();
            Set<String> keyNames = config.keySet();
            Pattern pattern = Pattern.compile(TEMPLATE_MODULE);
            for (String key : keyNames) {
                if (pattern.matcher(key).find()) {
                    templateModule.add(config.get(key));
                }
            }
        }
        return templateModule;
    }

    /**
     * 获取配置模块名称对应的ftl文件
     *
     * @param name
     * @return
     */
    public static String getTemplateModuleMatcherFtl(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return config.get("build.ftl." + name);
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(getTemplateModule()));
    }

    /**
     * 从配置文件获取构建模块配置属性
     *
     * @param name
     * @return {Map}
     */
    private static Map<String, String> getBuildBlock(String name) {

        Map<String, String> map = new HashMap<>();
        Set<String> keys = config.keySet();
        for (String keyStr : keys) {
            if (keyStr.contains(name)) {
                map.put(keyStr, config.get(keyStr));
            }
        }
        return map;
    }

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
    public static Map<String, String> getConfigType(String name) {
        Map<String, String> map = getBuildBlock(name);
        return map;
    }


    public static String getDirPath() {
        return dirPath;
    }

    public static void setDirPath(String dirPath) {
        TemplateConfig.dirPath = dirPath;
    }

    public static Map<String, String> getConfig() {
        return config;
    }
}
