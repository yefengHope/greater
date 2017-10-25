package ${config.packagePath};
<#--package com.fengyu.system.dao;-->
import java.util.List;
import java.util.Map;
import com.fengyu.system.base.BaseMapper;
import com.fengyu.system.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

<#assign modelName>
    <#if fullConfig["dao.impl.entity"]??>${fullConfig["dao.impl.entity"]}<#else>Object</#if>
</#assign>
<#assign modelLowerName>
    <#if fullConfig["dao.impl.entity"]??>${fullConfig["dao.impl.entity"]?uncap_first}<#else>map</#if>
</#assign>
/**
 * 自动生成代码
 * Created by HF on ${.now}.
 */
@Mapper
public interface ${config.className} extends BaseMapper<${modelName?trim}>{

   /**
    * 更新状态 id 集合in更新
    * @param status 被更新状态
    * @param ids id数组
    */
    void batchUpdateState(@Param("status") String status, @Param("ids") String [] ids);
}




