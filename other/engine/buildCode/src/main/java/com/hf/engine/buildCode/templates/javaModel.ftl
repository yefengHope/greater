<#assign configTableName>
    <#if fullConfig["db.tableName"]?? >
    ${fullConfig["db.tableName"]}
    </#if>
</#assign>
package ${config.packagePath};

import com.fengyu.system.base.BaseIdEntity;
import java.lang.*;
import java.math.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;
import com.fengyu.system.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 自动生成代码      <#--类注释-->
 * ${config.comment}        <#--类注释-->
 * @author ${config.author} <#--作者-->
 * @date ${.now}            <#--获取当前时间-->
 */
@Entity
@Table(name = "${configTableName?trim}")
public class ${config.className} extends BaseIdEntity{

    <#--如果data.fields 存在,则循环写入变量-->
    <#if data.fieldModels??>
        <#list data.fieldModels as fildModels>
        <#if fildModels.lowerCamelCaseName?matches("^((create)|(update))[A-Z][A-Za-z0-9]*")
            || fildModels.lowerCamelCaseName == "id">
        <#else>
        /**
        * ${fildModels.comment}
        */
        <#if fildModels.simpleDataTypeName == "Date">
        @JSONField(format="yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        </#if>
        @Column(name = "${fildModels.dbColumName}")
        private ${fildModels.simpleDataTypeName} ${fildModels.lowerCamelCaseName} ;
        </#if>
        </#list>
    </#if>

    <#--如果data.fields 存在,则循环写入GET/SET方法-->
    <#if data.fieldModels??>
        <#list data.fieldModels as fildModels>
            <#if fildModels.lowerCamelCaseName?matches("^((create)|(update))[A-Z][A-Za-z0-9]*")
            || fildModels.lowerCamelCaseName == "id">
            <#else>
        /**
        * 获取${fildModels.comment}
        */
        public ${fildModels.simpleDataTypeName} get${fildModels.upperCamelCaseName} () {
            return this.${fildModels.lowerCamelCaseName};
        }

        /**
        * 设置${fildModels.comment}
        */
        public void set${fildModels.upperCamelCaseName} (${fildModels.simpleDataTypeName} ${fildModels.lowerCamelCaseName}) {
            this.${fildModels.lowerCamelCaseName} = ${fildModels.lowerCamelCaseName};
        }
            </#if>
        </#list>
    </#if>
}

