package com.hf.adminWeb.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.hf.adminDao.entity.SystemSettingEntity;
import com.hf.adminService.service.SystemSettingService;
import com.hf.common.base.BaseController;
import com.hf.common.exception.BaseException;
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
* Created by HF on 2017-8-8 18:37:08.
*/
@Controller
@RequestMapping(value = "/admin/system/setting")
public class SystemSettingController extends BaseController {

    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(SystemSettingController.class);
    }

    @Resource
    private SystemSettingService systemSettingService;

    /**
    * 列表
    * bootstrap table server方式
    * bootstrap table调用toPageListJson生成json传回页面
    *
    * @return {String} 页面路径
    */
    @RequestMapping(value = "page.htm", method = RequestMethod.GET)
        public String toPage() {
        return "system/systemSetting/list";
    }

    /**
    * 列表,返回json(包含分页查询)
    *
    * @param pageSize   分页大小
    * @param pageNumber 当前页码
    * @param systemSettingEntity 查询参数
    * @return {Map} 返回Map结果
    */
    @RequestMapping(value = "page_data.json", method = RequestMethod.POST)
    @ResponseBody
    public Map pageJson(Integer pageNumber , Integer pageSize, SystemSettingEntity systemSettingEntity) throws BaseException {
        PageInfo<SystemSettingEntity> pageInfo = systemSettingService.findAllPageList(pageNumber,pageSize,systemSettingEntity);
        return returnBootTable(true,"查询成功",pageInfo);
    }

    /**
    * 跳转到添加页
    *
    * @return {String} 页面路径
    */
    @RequestMapping(value = "to_add.htm", method = RequestMethod.GET)
    public String toAdd(Model model) {
        SystemSettingEntity systemSettingEntity = (SystemSettingEntity) createObjFromClass(SystemSettingEntity.class);
        model.addAttribute("dataEntity", JSON.toJSONString(systemSettingEntity, SerializerFeature.WriteMapNullValue));
        return "system/systemSetting/form";
    }

    /**
    * 添加
    *
    * @param systemSettingEntity 实体
    * @return {Map} 返回Map结果
    */
    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public Map add(SystemSettingEntity systemSettingEntity) {
        if (systemSettingEntity != null && null == systemSettingEntity.getId() ){
            try {
                // TODO: 2017/10/7  
                // BaseEntity.setCreateAndUpdateUser(systemSettingEntity);
                systemSettingService.save(systemSettingEntity);
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
    public String toUpdate(Model model,SystemSettingEntity systemSettingEntity) throws BaseException {
        SystemSettingEntity systemSettingEntityOne = systemSettingService.findOne(systemSettingEntity);
        model.addAttribute("dataEntity", JSON.toJSONString(systemSettingEntityOne));
        return "system/systemSetting/form";
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    public Map update(SystemSettingEntity systemSettingEntity) {
        if (systemSettingEntity != null && null != systemSettingEntity.getId()){
            try {
                // TODO: 2017/10/7  
                // BaseEntity.setUpdateUser(systemSettingEntity);
                systemSettingService.update(systemSettingEntity);
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
            systemSettingService.batchUpdateState(ids,status);
            return returnAjax(true,"更新成功",null,null);
        } catch (Exception e) {
            logger.error("批量更新异常",e);
        }
        return returnAjax(false,"批量更新异常",null,null);
    }

}


