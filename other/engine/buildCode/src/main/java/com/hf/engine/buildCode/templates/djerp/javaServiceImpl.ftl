<#assign modelName>
    <#if fullConfig["service.impl.impl.Entity"]??>${fullConfig["service.impl.impl.Entity"]?trim}</#if>
</#assign>
<#assign modelLowerName>
    <#if fullConfig["service.impl.impl.Entity"]??>
        ${fullConfig["service.impl.impl.Entity"]?uncap_first}
    </#if>
</#assign>

<#assign mapperClassName>
    <#if fullConfig["service.impl.impl.Mapper"]??>${fullConfig["service.impl.impl.Mapper"]}</#if>
</#assign>
<#assign mapperClassLowerName>
    <#if fullConfig["service.impl.impl.Mapper"]??>${fullConfig["service.impl.impl.Mapper"]?uncap_first}</#if>
</#assign>
<#--package ${config.packagePath};-->

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

import cn.hlhdj.duoji.erp.model.base.service.impl.BaseServiceImpl;

/**
* Created by HF on ${.now}.
*/
@Service
public class ${config.className} extends BaseServiceImpl<${modelName?trim}> <#if fullConfig["service.impl.impl.service"]??>implements ${fullConfig["service.impl.impl.service"]}</#if> {
    private static Logger logger;
    static {
        logger = LoggerFactory.getLogger(${config.className}.class);
    }

    @Resource
    private ${mapperClassName?trim} ${mapperClassLowerName?trim};

    @Resource
    public void set${mapperClassName?trim} (${mapperClassName?trim} ${mapperClassLowerName?trim}) {
        this.${mapperClassLowerName?trim} = ${mapperClassLowerName?trim};
        super.setBaseMapper(${mapperClassLowerName?trim});
    }
}