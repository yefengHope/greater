package com.hf.engine.buildCode.codePart;

import com.hf.engine.buildCode.model.FieldModel;

import java.util.List;

/**
 * Created by 韩峰 on 2017/11/23.
 */
public class BootstrapTableCodePart {

    public String buildColumns(List<FieldModel> fieldModels) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("\n");
        for (FieldModel fieldModel : fieldModels) {
            sb.append("     {");
            sb.append("\n");
            if ("id".equals(fieldModel.getDbColumName())) {
                sb.append("         idField:");

            } else {
                sb.append("         field:");
            }
            sb.append("'");
            sb.append(fieldModel.getLowerCamelCaseName());
            sb.append("'");
            sb.append(",");
            sb.append("\n");
            if ("id".equals(fieldModel.getDbColumName())) {

                sb.append("     checkbox: true,");
            }
            sb.append("         title:");
            sb.append("'");
            sb.append(fieldModel.getComment());
            sb.append("'");
            sb.append(",");
            sb.append("\n");

            sb.append("         align: 'center',");
            sb.append("\n");
            sb.append("         valign: 'middle',");
            sb.append("\n");

            sb.append("     },");
            sb.append("\n");
        }
        sb.append("]");
        sb.append("\n");
        return sb.toString();
    }
}
