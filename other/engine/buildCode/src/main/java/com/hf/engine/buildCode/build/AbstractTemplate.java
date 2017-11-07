package com.hf.engine.buildCode.build;

import com.hf.common.FileUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * <p>@Title 类标题描述 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/6/19 17:04 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public abstract class AbstractTemplate {

    /**
     * 获取模板, 文件夹路径在配置文件中设置
     * @param fileName  ftl文件名
     * @param dirPath   模板文件夹路径
     * @return {Template}
     * @throws IOException
     */
    public Template getTemplate(String fileName,String dirPath) throws IOException {
        Configuration cfg;
        //step1 创建一个Configuration实例
        cfg = new Configuration();

        //step2 设置配置文件中的template路径
        File templateFile = FileUtils.getFile(dirPath);
        if (!templateFile.exists()) {
            throw new NullPointerException("模板文件夹路径无效");
        }
        // cfg.setClassForTemplateLoading(getClass(), templateFile.getPath());
        // cfg.setClassForTemplateLoading(getClass(), TemplateConfig.getDirPath());
        cfg.setDirectoryForTemplateLoading(templateFile);

        //step3 设置freemarker模板编码
        cfg.setEncoding(Locale.getDefault(), "UTF-8");

        // step4 找到对应freemarker模板并实例化
        Template template = cfg.getTemplate(fileName);
        if (template == null) {
            throw new NullPointerException("文件名无效");
        }
        // 返回模板
        return template;
    }

    /**
     * 模板处理之前
     *
     * @return
     */
    public abstract Map doBefore(Map<String, Object> map);

    /**
     * 模板处理,模板生成
     * @param map
     * {
     *     fileName : ftl文件名
     *     filePath : 模板生成文件输出路径
     * }
     * @throws IOException
     */
    public abstract void doHandle(Map<String, Object> map);

    /**
     * 模板之后
     * @return
     */
    public abstract Map doAfter(Map<String, Object> map);
}
