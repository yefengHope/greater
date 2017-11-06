package com.hf.engine.buildCode.init;


import com.alibaba.fastjson.JSONObject;
import com.hf.engine.buildCode.build.CreateGeneralTemplate;
import com.hf.engine.buildCode.model.FieldModel;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by HanFeng on 2017/6/24.
 */
public abstract class AbstractInit {


    /**
     * 默认初始化方法
     *
     * @param name 模块名称
     */
    public void defaultInit(String name,List<FieldModel> fieldModels,JSONObject configModulAttr,JSONObject config,String matchStr) {

        String build = null;
        String fileName = null;
        String comment = null;
        String packagePath = null;
        String fileType = null;
        Set<String> attrKeys = configModulAttr.keySet();
        for (String keyName : attrKeys) {
            if (keyName.contains("build."+name)) {
                build = configModulAttr.getString(keyName);
            }
            if (keyName.contains("fileName")) {
                fileName = configModulAttr.getString(keyName);
            }
            if (keyName.contains("comment")) {
                comment = configModulAttr.getString(keyName);
            }
            if (keyName.contains("packagePath")) {
                packagePath = configModulAttr.getString(keyName);
            }
            if (keyName.contains("fileType")) {
                fileType = configModulAttr.getString(keyName);
            }
        }
        // 如果配置为true且包路径不为空，则生成模块内容
        if ("true".equals(build) && StringUtils.isNotBlank(packagePath)) {
            CreateGeneralTemplate createTemplate = new CreateGeneralTemplate();
            Map<String, Object> fileMap = new HashMap<>();
            Map<String, Object> dataMap = new HashMap<>();
            Map<String, Object> configMap = new HashMap<>();
            if (StringUtils.isBlank(fileName)) {
                throw new RuntimeException("模块异常 : 没有文件名");
            }
            configMap.put("fileName", fileName);
            configMap.put("className", fileName);
            configMap.put("author", "HF");
            configMap.put("comment", comment);
            configMap.put("packagePath", packagePath);
            dataMap.put("fieldModels", fieldModels);
            // ftl的数据表内容结构
            fileMap.put("data", dataMap);                           /*数据库字段配置*/
            // ftl的配置的文件数据
            fileMap.put("fullConfig", config);  /*当前文件中所有配置*/
            fileMap.put("config", configMap);                       /*当前方法中处理过的数据*/
            fileMap.put("fileName", matchStr);                /*匹配模版文件名*/
            // 配置文件
            String templatePackagePath = config.getString("template.package.path");

            // 获取配置模块内容
            // String[] configModules = TemplateConfig.getTemplateModule();

            fileMap.put("templatePackagePath", templatePackagePath);
            fileMap.put("filePath", Class.class.getClass().getResource("/").getPath()
                    + File.separator + "src"
                    + File.separator + "main"
                    + File.separator + "java"
                    + File.separator
                    + packagePath.replace(".", File.separator)
                    + File.separator + fileName
                    + fileType
            );

            createTemplate.doHandle(fileMap);
        } else {
            throw new RuntimeException("异常 : build 不等于true or packagePath 为空");
        }
    }
}
