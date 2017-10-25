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
package ${config.packagePath};

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* 自动生成代码
* Created by HF on ${.now}.
*/
@Service("${config.className?uncap_first}")
public class ${config.className} <#if fullConfig["service.impl.impl.service"]??>implements ${fullConfig["service.impl.impl.service"]}</#if> {
    private static Logger logger;
    static {
        logger = LoggerFactory.getLogger(${config.className}.class);
    }

    @Resource
    private ${mapperClassName?trim} ${mapperClassLowerName?trim};

    @Override
    public ${modelName?trim} findOne(${modelName?trim} ${modelLowerName?trim}) {
        return ${mapperClassLowerName?trim}.selectOne(${modelLowerName?trim});
    }

    @Override
    public List<${modelName?trim}> findAllList(${modelName?trim} ${modelLowerName?trim}) {
        return ${mapperClassLowerName?trim}.select(${modelLowerName?trim});
    }

    @Override
    public PageInfo<${modelName?trim}> findAllPageList(Integer pageNumber, Integer pageSize, ${modelName?trim} ${modelLowerName?trim}) {
        if( pageNumber!= null && pageSize!= null){
            PageHelper.startPage(pageNumber, pageSize);
        }
        List<${modelName?trim}> list = ${mapperClassLowerName?trim}.select(${modelLowerName?trim});
        return new PageInfo<${modelName?trim}> (list);
    }

    @Override
    public void save(${modelName?trim} ${modelLowerName?trim}) {
        if (${modelLowerName?trim} != null) {
            ${mapperClassLowerName?trim}.insert(${modelLowerName?trim});
        } else {
            logger.error("保存数据，但是数据不存在");
        }
    }

    @Override
    public void update(${modelName?trim} ${modelLowerName?trim}) {
        if (${modelLowerName?trim} != null && null != ${modelLowerName?trim}.getId()) {
            Example example = new Example(${modelName?trim}.class);
            example.createCriteria().andEqualTo("id", ${modelLowerName?trim}.getId());
            ${mapperClassLowerName?trim}.updateByExampleSelective(${modelLowerName?trim}, example);
        } else {
            logger.error("更新数据，但是数据不存在");
        }
    }

    @Override
    public void batchUpdateState(String ids,String status) {
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
            if (idArr != null && idArr.length > 0 && StringUtils.isNotBlank(status)){
                ${mapperClassLowerName?trim}.batchUpdateState(status,idArr);
            }
        }
    }
}