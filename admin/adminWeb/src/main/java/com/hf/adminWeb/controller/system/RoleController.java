package com.hf.adminWeb.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.hf.adminDao.entity.RoleEntity;
import com.hf.adminService.service.RoleService;
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
 * 角色controller
 * Created by HanFeng on 2017/7/28.
 */
@Controller
@RequestMapping(value = "/admin/role")
public class RoleController extends BaseController {

    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(RoleController.class);
    }

    @Resource
    private RoleService roleService;

    /**
     * 列表
     * bootstrap table server方式
     * bootstrap table调用toPageListJson生成json传回页面
     *
     * @return {String} 页面路径
     */
    @RequestMapping(value = "page.htm", method = RequestMethod.GET)
    public String toPage() {
        return "system/role/list";
    }

    /**
     * 列表,返回json(包含分页查询)
     *
     * @param pageSize   分页大小
     * @param pageNumber 当前页码
     * @param roleEntity 查询参数
     * @return {Map} 返回Map结果
     */
    @RequestMapping(value = "page_data.json", method = RequestMethod.POST)
    @ResponseBody
    public Map pageJson(Integer pageNumber , Integer pageSize, RoleEntity roleEntity) throws BaseException {
        PageInfo<RoleEntity> pageInfo = roleService.findAllPageList(pageNumber,pageSize,roleEntity);
        return returnBootTable(true,"查询成功",pageInfo);
    }

    /**
     * 跳转到添加页
     *
     * @return {String} 页面路径
     */
    @RequestMapping(value = "to_add.htm", method = RequestMethod.GET)
    public String toAdd(Model model) {
        RoleEntity user = (RoleEntity) createObjFromClass(RoleEntity.class);
        model.addAttribute("dataEntity", JSON.toJSONString(user, SerializerFeature.WriteMapNullValue));
        return "system/role/form";
    }

    /**
     * 添加
     *
     * @param roleEntity 角色实体
     * @return {Map} 返回Map结果
     */
    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public Map add(RoleEntity roleEntity) {
        if (roleEntity != null && null == roleEntity.getId() ){
            try {
                // TODO: 2017/10/7
                // BaseEntity.setCreateAndUpdateUser(roleEntity);
                roleService.save(roleEntity);
            } catch (Exception e) {
                logger.error("新增异常",e);
                return returnAjax(false,e.getMessage(),null,null);
            }
            return returnAjax(true,"保存成功",null,null);
        } else {
            logger.error("新增，参数不存在或者参数存在id");
            return returnAjax(false,"参数异常",null,null);
        }
    }

    /**
     * 获取单条数据
     *
     * @return {String} 页面路径
     */
    @RequestMapping(value = "getSingleRole.do", method = RequestMethod.POST)
    @ResponseBody
    public Map getSingleDate(RoleEntity roleEntity) throws BaseException {
        RoleEntity roleServiceOne = roleService.findOne(roleEntity);
        return returnAjax(true,"查询成功",roleServiceOne,null);
    }

    /**
     * 跳转到更新页
     *
     * @return {String} 页面路径
     */
    @RequestMapping(value = "to_update.htm", method = RequestMethod.GET)
    public String toUpdate(Model model,RoleEntity roleEntity) throws BaseException {
        RoleEntity roleServiceOne = roleService.findOne(roleEntity);
        model.addAttribute("dataEntity", JSON.toJSONString(roleServiceOne));
        return "system/role/form";
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    public Map update(RoleEntity role) {
        if (role != null && null != role.getId()){
            try {
                // TODO: 2017/10/7
                // BaseEntity.setUpdateUser(role);
                roleService.update(role);
            } catch (Exception e) {
                logger.error("修改基础数据异常",e);
                return returnAjax(false,"修改基础数据异常",null,null);
            }
            return returnAjax(true,"保存成功",null,null);
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
            roleService.batchUpdateState(ids,status);
            return returnAjax(true,"更新成功",null,null);
        } catch (Exception e) {
            logger.error("批量更新异常",e);
        }
        return returnAjax(false,"批量更新异常",null,null);
    }

}
