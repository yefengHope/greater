package com.hf.engine.buildCode;


import com.alibaba.fastjson.JSONObject;
import com.hf.engine.buildCode.config.CodeFactoryConfig;
import com.hf.engine.buildCode.config.IFileConfig;
import com.hf.engine.buildCode.config.ITemplateConfig;
import com.hf.engine.buildCode.config.impl.PropertiesConfig;
import com.hf.engine.buildCode.config.impl.TemplateConfigHelper;
import com.hf.engine.buildCode.init.AbstractInit;
import com.hf.engine.buildCode.init.InitCommon;
import com.hf.engine.buildCode.jdbc.JdbcConfig;
import com.hf.engine.buildCode.jdbc.JdbcResult;
import com.hf.engine.buildCode.jdbc.JdbcResultConvert;
import com.hf.engine.buildCode.jdbc.JdbcResultImpl;
import com.hf.engine.buildCode.model.FieldModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 创建
 * Created by rain on 2017/6/19.
 */
public class MainCreate {
    private static Logger logger = Logger.getLogger(MainCreate.class);


    public static void main(String[] args) {

        // String userDir = System.getProperty("user.dir");
        // System.out.println(userDir); // C:\WorkSpace\greater
        //
        // String classLoadPath = Class.class.getClass().getResource("/").getPath();
        // // /C:/WorkSpace/greater/other/engine/buildCode/target/classes/
        // System.out.println(classLoadPath);


//        CodeFactoryConfig.configProp("codeFactoryConfig");
        createFile();
        // System.out.println(System.getProperty("user.dir"));
        // try {
        //     Object snacksModel = Class.forName("com.fengyu.engine.codecreatorFrame.file.SnacksModel");
        //     System.out.println(snacksModel);
        // } catch (ClassNotFoundException e) {
        //     e.printStackTrace();
        // }
    }


    public static void createFile() {

        // 获取代码生成器配置文件的map
        IFileConfig config = new PropertiesConfig();
        JSONObject configMap = config.getConfig("codeFactoryDjConfig");
        // 获取配置模块
        ITemplateConfig templateConfig = new TemplateConfigHelper();
        // 获取配置模块
        List<String> moduleNames = templateConfig.getTemplateModule(configMap,"template.module.match");
        // 设置数据库配置
        JdbcConfig jdbcConfig = new JdbcConfig(configMap);
        // 获取数据库字段
        JdbcResult jdbcResult = new JdbcResultImpl();
        // "select * from information_schema.columns where table_name='" + jdbcConfig.getTableName() + "' "
        List<Map<String, Object>> tableStructure = jdbcResult.getTableStructureList(jdbcConfig);
        // 获取字段转换结果
        List<FieldModel> fieldModels = JdbcResultConvert.formatColumn(tableStructure);

        // 生成文件
        AbstractInit init = new InitCommon();
        for (String moduleName : moduleNames) {
            JSONObject configModulAttr = templateConfig.getConfigType(configMap,moduleName);
            // ftl模板名
            String matchStr = templateConfig.getTemplateModuleMatcherFtl(configMap,moduleName);
            try {
                init.defaultInit(moduleName,fieldModels,configModulAttr,configMap,matchStr);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    /**
     * 包路径转换成文件夹路径
     *
     * @param packagePath
     * @return
     */
    public String packageToDir(String packagePath) {
        if (StringUtils.isBlank(packagePath)) {
            return null;
        }
        String[] packagePaths = packagePath.split(".");
        if (packagePaths.length <= 0) {
            return null;
        }
        return StringUtils.join(packagePaths, File.separator);
    }

    /**
     * 文件夹路径转换成包路径
     *
     * @param dirPath
     * @return
     */
    public String dirToPackagePath(String dirPath) {
        if (StringUtils.isBlank(dirPath)) {
            return null;
        }
        String[] dirPaths = dirPath.split(".");
        if (dirPaths.length <= 0) {
            return null;
        }
        return StringUtils.join(dirPaths, ".");
    }
}
