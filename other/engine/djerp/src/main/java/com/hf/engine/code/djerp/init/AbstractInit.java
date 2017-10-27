package com.hf.engine.code.djerp.init;


import com.hf.engine.code.djerp.build.CreateGeneralTemplate;
import com.hf.engine.code.djerp.build.TemplateConfig;
import com.hf.engine.code.djerp.config.CodeFactoryConfig;
import com.hf.engine.code.djerp.jdbc.JdbcResult;
import com.hf.engine.code.djerp.jdbc.JdbcResultConvert;
import com.hf.engine.code.djerp.jdbc.JdbcResultImpl;
import com.hf.engine.code.djerp.model.FieldModel;
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


    public abstract void init();

    private static List<Map<String, Object>> dataStructureList;
    private static List<FieldModel> fieldModels;

    static {
        JdbcResult jdbcResult = new JdbcResultImpl();
        dataStructureList = jdbcResult.getTableStructureList();
        fieldModels = JdbcResultConvert.formatColumn(dataStructureList);
    }


    /**
     * 根据配置内容获取数据表结构
     *
     * @return {List<Map<String, Object>>}
     */
    public List<Map<String, Object>> selectDataStructureList() {
        return dataStructureList;
    }

    /**
     * 转换数表结构
     *
     * @param list 查询出来的数据库的表结构
     * @return {List<FieldModel>}
     */
    public List<FieldModel> selectFieldModels(List<Map<String, Object>> list) {
        return fieldModels;
    }

    /**
     * 转换数表结构
     * <p>已经内部查询数据库表结构
     *
     * @return {List<FieldModel>}
     */
    public List<FieldModel> selectFieldModels() {
        List<Map<String, Object>> list = selectDataStructureList();
        List<FieldModel> fieldModels = JdbcResultConvert.formatColumn(list);
        return fieldModels;
    }


    /**
     * 根据模块名称匹配模块使用的ftl文件
     */
    public String matchFtl(String name) {
        String matchStr = TemplateConfig.getTemplateModuleMatcherFtl(name);
        if (StringUtils.isEmpty(matchStr)) {
            throw new RuntimeException("未找到对应的模板");
        }
        return matchStr;
    }

    /**
     * 默认初始化方法
     *
     * @param name 模块名称
     */
    public void defaultInit(String name) {
        Map<String, String> configModulAttr = TemplateConfig.getConfigType(name);

        String build = null;
        String fileName = null;
        String comment = null;
        String packagePath = null;
        String fileType = null;
        Set<String> attrKeys = configModulAttr.keySet();
        for (String keyName : attrKeys) {
            if (keyName.contains("build."+name)) {
                build = configModulAttr.get(keyName);
            }
            if (keyName.contains("fileName")) {
                fileName = configModulAttr.get(keyName);
            }
            if (keyName.contains("comment")) {
                comment = configModulAttr.get(keyName);
            }
            if (keyName.contains("packagePath")) {
                packagePath = configModulAttr.get(keyName);
            }
            if (keyName.contains("fileType")) {
                fileType = configModulAttr.get(keyName);
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
            dataMap.put("fieldModels", selectFieldModels());
            // ftl的数据表内容结构
            fileMap.put("data", dataMap);                           /*数据库字段配置*/
            // ftl的配置的文件数据
            fileMap.put("fullConfig", TemplateConfig.getConfig());  /*当前文件中所有配置*/
            fileMap.put("config", configMap);                       /*当前方法中处理过的数据*/
            fileMap.put("fileName", matchFtl(name));                /*匹配模版文件名*/
            // 配置文件
            Map<String, String> config = CodeFactoryConfig.getConfig();
            String templatePackagePath = config.get("template.package.path");

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
