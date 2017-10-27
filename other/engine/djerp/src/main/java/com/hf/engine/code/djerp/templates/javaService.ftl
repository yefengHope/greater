<#assign modelName>
    <#if fullConfig["service.impl.entity"]??>${fullConfig["service.impl.entity"]}</#if>
</#assign>
<#assign modelLowerName>
    <#if config.modelName??>${config.modelName?uncap_first}<#else>map</#if>
</#assign>
package ${config.packagePath};

import com.fengyu.system.base.BaseService;
/**
* 自动生成代码
* Created by HF on ${.now}.
*/
public interface ${config.className} extends BaseService<${modelName?trim}>{

}