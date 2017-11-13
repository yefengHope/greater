package com.hf.engine.buildCode.codePart;

import com.hf.engine.buildCode.model.FieldModel;

import java.util.List;

/**
 * Created by 韩峰 on 2017/11/7.
 */
public class MapperCodePart {

    public String resultMap(List<FieldModel> fieldModels,String resultMapId,String resultMapType) {
        StringBuilder sb = new StringBuilder();
        sb.append("<resultMap");
        sb.append(" id=\"");
        sb.append(resultMapId);
        sb.append("\"");
        sb.append(" type=\"");
        sb.append(resultMapType);
        sb.append("\"");
        sb.append(">");
        sb.append("\n");
        for (FieldModel fieldModel : fieldModels) {
            if ("id".equals(fieldModel.getDbColumName())) {
                sb.append("     <id");
            } else {
                sb.append("     <result");
            }
            sb.append(" column=\"");
            sb.append(fieldModel.getDbColumName());
            sb.append("\"");
            sb.append(" property=\"");
            sb.append(fieldModel.getLowerCamelCaseName());
            sb.append("\"");
            sb.append("/");
            sb.append(">");
            sb.append("\n");
        }
        sb.append("</resultMap>");
        sb.append("\n");
        return sb.toString();
    }
}
