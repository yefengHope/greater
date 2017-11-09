package com.hf.engine.buildCode.codePart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hf.engine.buildCode.model.FieldModel;

import java.util.List;

/**
 * Created by HF on 2017/11/7.
 */
public class HtmlCodePart {

    /**
     * 创建html页面
     *
     * @return {String}
     */
    public String buildPage(List<FieldModel> fieldModels, JSONObject params) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        return sb.toString();
    }

    /**
     * 创建基于layui风格的table显示信息
     *
     * @param fieldName   需要显示的字段list
     * @param fieldModels 字段数据模型
     * @param params      设置参数 {
     *                    table.id
     *                    table.class
     *                    table.title
     *                    table.rowNum
     *                    table.colNum
     * }
     * @return
     */
    public String buildLayuiTableShowInfo(List<String> fieldName, List<FieldModel> fieldModels, JSONObject params) {

        StringBuilder sb = new StringBuilder();
        sb.append("<table");

        sb.append(" id=\"");
        sb.append(params.getString("table.id"));
        sb.append("\"");

        sb.append(" class=\"layui-table");
        JSONArray tableClass = params.getJSONArray("table.class");
        for (Object htmlClass: tableClass) {
            sb.append(" ");
            sb.append(htmlClass);
        }
        sb.append(">");



        sb.append("");
        sb.append("");
        sb.append("</table>");
        return sb.toString();
    }
}
