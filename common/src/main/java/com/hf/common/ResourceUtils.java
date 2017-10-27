package com.hf.common;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 资源文件工具类
 *
 * @author healy
 */
public class ResourceUtils {

    private ResourceBundle resourceBundle;

    private ResourceUtils(String resource) {
        resourceBundle = ResourceBundle.getBundle(resource);
    }

    /**
     * 获取资源
     *
     * @param resource 资源
     * @return 解析
     */
    public static ResourceUtils getResource(String resource) {
        return new ResourceUtils(resource);
    }

    /**
     * 根据key取得value
     *
     * @param key  键值
     * @param args value中参数序列，参数:{0},{1}...,{n}
     * @return
     */
    public String getValue(String key, Object... args) {
        String temp = resourceBundle.getString(key);
        return MessageFormat.format(temp, args);
    }

    /**
     * 获取所有资源的Map表示
     *
     * @return 资源Map
     */
    public Map<String, String> getMap() {
        Pattern pattern = Pattern.compile("\\$\\{[^\\}]+\\}");
        Map<String, String> map = new HashMap<String, String>();
        for (String key : resourceBundle.keySet()) {
            String value = resourceBundle.getString(key);
            Matcher matcher = pattern.matcher(value);
            System.out.println(value);
            if ("${service.fileName}".equals(value)) {
                System.out.println();
            }
            String g;
            while (matcher.find()) {
                g = matcher.group();
                String gt = g.substring(2, g.length() - 1);
                value = value.replace(g,resourceBundle.getString(gt));
                matcher = pattern.matcher(value);
            }

            map.put(key, value);
        }
        return map;
    }
}
