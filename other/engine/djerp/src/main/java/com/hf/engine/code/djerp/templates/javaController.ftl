<#assign ftlPageRequestMapping>
    <#if fullConfig["controller.requestMapping"]??>${fullConfig["controller.requestMapping"] ?trim}</#if>
</#assign>
<#assign ftlPageImplService>
    <#if fullConfig["controller.impl.Service"]??>${fullConfig["controller.impl.Service"] ?trim}</#if>
</#assign>
<#assign ftlPageImplEntity>
    <#if fullConfig["controller.impl.entity"]??>${fullConfig["controller.impl.entity"] ?trim}</#if>
</#assign>
<#assign ftlPageFtlPrefix>
    <#if fullConfig["controller.impl.ftl.prefix"]??>${fullConfig["controller.impl.ftl.prefix"] ?trim}</#if>
</#assign>

<#assign configRequestMapping>
    <#if ftlPageRequestMapping??>${ftlPageRequestMapping?trim}</#if>
</#assign>
<#assign configImplService>
    <#if ftlPageImplService??>${ftlPageImplService?trim}</#if>
</#assign>
<#assign configImplEntity>
    <#if ftlPageImplEntity??>${ftlPageImplEntity?trim}</#if>
</#assign>
<#assign configFtlPrefix>
    <#if ftlPageFtlPrefix??>${ftlPageFtlPrefix?trim}</#if>
</#assign>

package ${config.packagePath};

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fengyu.system.base.BaseController;
import com.fengyu.system.base.BaseEntity;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
* 自动生成代码
* Created by HF on ${.now}.
*/
@Controller
@RequestMapping(value = "${configRequestMapping?trim}")
public class ${config.className} extends BaseController {

    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(${config.className}.class);
    }

    @Resource
    private ${configImplService?trim} ${configImplService?uncap_first?trim};

    /**
    * 列表
    * bootstrap table server方式
    * bootstrap table调用toPageListJson生成json传回页面
    *
    * @return {String} 页面路径
    */
    @RequestMapping(value = "page.htm", method = RequestMethod.GET)
        public String toPage() {
        return "${configFtlPrefix?trim}/list";
    }

    /**
    * 列表,返回json(包含分页查询)
    *
    * @param pageSize   分页大小
    * @param pageNumber 当前页码
    * @param ${configImplEntity?uncap_first?trim} 查询参数
    * @return {Map} 返回Map结果
    */
    @RequestMapping(value = "page_data.json", method = RequestMethod.POST)
    @ResponseBody
    public Map pageJson(Integer pageNumber , Integer pageSize, ${configImplEntity?trim} ${configImplEntity?uncap_first?trim}) {
    PageInfo<${configImplEntity?trim}> pageInfo = ${configImplService?uncap_first?trim}.findAllPageList(pageNumber,pageSize,${configImplEntity?uncap_first?trim});
        return returnBootTable(true,"查询成功",pageInfo);
    }

    /**
    * 跳转到添加页
    *
    * @return {String} 页面路径
    */
    @RequestMapping(value = "to_add.htm", method = RequestMethod.GET)
    public String toAdd(Model model) {
        ${configImplEntity?trim} ${configImplEntity?uncap_first?trim} = (${configImplEntity?trim}) createObjFromClass(${configImplEntity?trim}.class);
        model.addAttribute("dataEntity", JSON.toJSONString(${configImplEntity?uncap_first?trim}, SerializerFeature.WriteMapNullValue));
        return "${configFtlPrefix?trim}/form";
    }

    /**
    * 添加
    *
    * @param ${configImplEntity?uncap_first?trim} 实体
    * @return {Map} 返回Map结果
    */
    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public Map add(${configImplEntity?trim} ${configImplEntity?uncap_first?trim}) {
        if (${configImplEntity?uncap_first?trim} != null && null == ${configImplEntity?uncap_first?trim}.getId() ){
            try {
                BaseEntity.setCreateAndUpdateUser(${configImplEntity?uncap_first?trim});
                ${configImplService?uncap_first?trim}.save(${configImplEntity?uncap_first?trim});
                return returnAjax(true,"保存成功",null,null);
            } catch (Exception e) {
                logger.error("新增异常",e);
                return returnAjax(false,e.getMessage(),null,null);
            }
        } else {
            logger.error("新增，参数不存在或者参数存在id");
            return returnAjax(false,"参数异常",null,null);
        }
    }

    /**
    * 跳转到更新页
    *
    * @return {String} 页面路径
    */
    @RequestMapping(value = "to_update.htm", method = RequestMethod.GET)
    public String toUpdate(Model model,${configImplEntity?trim} ${configImplEntity?uncap_first?trim}) {
        ${configImplEntity?trim} ${configImplEntity?uncap_first?trim}One = ${configImplService?uncap_first?trim}.findOne(${configImplEntity?uncap_first?trim});
        model.addAttribute("dataEntity", JSON.toJSONString(${configImplEntity?uncap_first?trim}One, SerializerFeature.WriteMapNullValue));
        return "${configFtlPrefix?trim}/form";
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    public Map update(${configImplEntity?trim} ${configImplEntity?uncap_first?trim}) {
        if (${configImplEntity?uncap_first?trim} != null && null != ${configImplEntity?uncap_first?trim}.getId()){
            try {
                BaseEntity.setUpdateUser(${configImplEntity?uncap_first?trim});
                ${configImplService?uncap_first?trim}.update(${configImplEntity?uncap_first?trim});
                return returnAjax(true,"保存成功",null,null);
            } catch (Exception e) {
                logger.error("修改基础数据异常",e);
                return returnAjax(false,"修改基础数据异常",null,null);
            }
        } else {
            logger.error("修改基础数据，参数不存在或者参数存在id");
            return returnAjax(false,"参数异常",null,null);
        }
    }

    /**
    * 批量更新状态
    * @param ids       id字符串， 1,2,3,...形式
    * @param status    状态代码
    * @return
    */
    @RequestMapping(value = "batch_update_state.do", method = RequestMethod.POST)
    @ResponseBody
    public Map delRows(String ids,String status) {
        try {
            ${configImplService?uncap_first?trim}.batchUpdateState(ids,status);
            return returnAjax(true,"更新成功",null,null);
        } catch (Exception e) {
            logger.error("批量更新异常",e);
        }
        return returnAjax(false,"批量更新异常",null,null);
    }

}


