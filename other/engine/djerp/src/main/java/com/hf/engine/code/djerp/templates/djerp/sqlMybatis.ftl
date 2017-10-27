<#--传输数据说明 :
 ${config.*} 为配置信息
 ${data.*}为数据库读取信息
 data.fields 为数据区读取字段
 config.packagePath 配置路径
 config.className   配置类名

 字段接收说明 :
 data.fieldModels {List<Map>} 字段集合List
 fildModels.comment 字段注释
 fildModels.type 字段的java类型
 fildModels.name 字段的java名称 小驼峰
 fildModels.Name 字段的java名称 大驼峰

 -->
<#assign configTableName>
    <#if fullConfig["db.tableName"]?? >
        ${fullConfig["db.tableName"]}
    </#if>
</#assign>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${config.packagePath}.${config.className}">
    <update id="batchUpdateState">
        UPDATE ${configTableName?trim} SET status = ${r'#{status}'}
        WHERE
        id in
        <foreach collection="ids" item="id" open="(" close=")" separator=",">
            ${r'#{id}'}
        </foreach>
    </update>
</mapper>