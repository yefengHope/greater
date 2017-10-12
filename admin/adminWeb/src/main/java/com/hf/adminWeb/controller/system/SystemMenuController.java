

package com.hf.adminWeb.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.hf.adminDao.entity.SystemMenuEntity;
import com.hf.adminService.service.SystemMenuService;
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
import java.util.List;
import java.util.Map;

/**
 * 自动生成代码
 * Created by HF on 2017-8-8 22:26:54.
 */
@Controller
@RequestMapping(value = "/admin/system/menu")
public class SystemMenuController extends BaseController {

    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(SystemMenuController.class);
    }

    @Resource
    private SystemMenuService systemMenuService;

    /**
     * 列表
     * bootstrap table server方式
     * bootstrap table调用toPageListJson生成json传回页面
     *
     * @return {String} 页面路径
     */
    @RequestMapping(value = "/page.htm", method = RequestMethod.GET)
    public String toPage() {
        return "system/menu/list";
    }

    /**
     * 树形列表
     */
    @RequestMapping(value = "/page_tree.htm", method = RequestMethod.GET)
    public String toTreeList(Model model) throws BaseException {
        SystemMenuEntity selectWhere = new SystemMenuEntity();
        selectWhere.setStatus(1);
        List<SystemMenuEntity> list = systemMenuService.findAllList(selectWhere);
        model.addAttribute("menuEntityList",
                JSON.toJSONString(list));
        model.addAttribute("dataEntity"
                , JSON.toJSONString(new SystemMenuEntity(), SerializerFeature.WriteMapNullValue));
        return "system/menu/treeList";
    }

    /**
     * 列表,返回json(包含分页查询)
     *
     * @param pageSize         分页大小
     * @param pageNumber       当前页码
     * @param systemMenuEntity 查询参数
     * @return {Map} 返回Map结果
     */
    @RequestMapping(value = "/page_data.json", method = RequestMethod.POST)
    @ResponseBody
    public Map pageJson(Integer pageNumber, Integer pageSize, SystemMenuEntity systemMenuEntity) throws BaseException {
        PageInfo<SystemMenuEntity> pageInfo = systemMenuService.findAllPageList(pageNumber, pageSize, systemMenuEntity);
        return returnBootTable(true, "查询成功", pageInfo);
    }

    /**
     * 跳转到添加页
     *
     * @return {String} 页面路径
     */
    @RequestMapping(value = "/to_add.htm", method = RequestMethod.GET)
    public String toAdd(Model model) {
        SystemMenuEntity systemMenuEntity = (SystemMenuEntity) createObjFromClass(SystemMenuEntity.class);
        model.addAttribute("dataEntity", JSON.toJSONString(systemMenuEntity, SerializerFeature.WriteMapNullValue));
        return "system/menu/form";
    }

    /**
     * 添加
     * ps ： 必须存在父级菜单，因为数据库已经默认添加根节点，如果没有则会异常
     *
     * @param systemMenuEntity 实体
     * @return {Map} 返回Map结果
     */
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    @ResponseBody
    public Map add(SystemMenuEntity systemMenuEntity) throws BaseException {
        if (
                systemMenuEntity != null
                        && null == systemMenuEntity.getId()
                ) {

            // 必须存在父级菜单，因为数据库已经默认添加根节点，如果没有则会异常

            // menuType : 1用户端菜单 2后台管理菜单
            Long proMenuId = systemMenuEntity.getProMenuId();
            if (null == proMenuId) {
                return returnAjax(false, "【添加菜单】：父级菜单缺失", null, null);
            }

            SystemMenuEntity selectWhere = new SystemMenuEntity();
            selectWhere.setId(systemMenuEntity.getProMenuId());
            SystemMenuEntity proMenuEntity
                    = systemMenuService.findOne(selectWhere);
            if (null == proMenuEntity) {
                return returnAjax(false, "【添加菜单】：找不到父级菜单", null, null);
            }
            try {
                /*
                    设置父级菜单id
                    设置父级菜单类型，和父级菜单类型一致
                    设置菜单层级 所有的父级菜单以"_"为连接符的id拼接字符串
                 */
                systemMenuEntity.setProMenuId(proMenuEntity.getId());
                systemMenuEntity.setMenuType(proMenuEntity.getMenuType());
                systemMenuEntity.setMenuLevels(proMenuEntity.getMenuLevels() + "_" + proMenuEntity.getId());
                // TODO: 2017/10/7
                // BaseEntity.setCreateAndUpdateUser(systemMenuEntity);
                systemMenuService.save(systemMenuEntity);
                JSONObject retJson = new JSONObject();
                retJson.put("id", systemMenuEntity.getId());
                return returnAjax(true, "保存成功", retJson, null);
            } catch (Exception e) {
                logger.error("新增异常", e);
                return returnAjax(false, e.getMessage(), null, null);
            }
        } else {
            logger.error("新增，参数不存在或者参数存在id");
            return returnAjax(false, "参数异常", null, null);
        }
    }

    /**
     * 跳转到更新页
     *
     * @return {String} 页面路径
     */
    @RequestMapping(value = "/to_update.htm", method = RequestMethod.GET)
    public String toUpdate(Model model, SystemMenuEntity systemMenuEntity) throws BaseException {
        SystemMenuEntity systemMenuEntityOne = systemMenuService.findOne(systemMenuEntity);
        model.addAttribute("dataEntity", JSON.toJSONString(systemMenuEntityOne, SerializerFeature.WriteMapNullValue));
        return "system/menu/form";
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public Map update(SystemMenuEntity systemMenuEntity) {
        if (systemMenuEntity != null && null != systemMenuEntity.getId()) {
            try {
                // TODO: 2017/10/7
                // BaseEntity.setUpdateUser(systemMenuEntity);
                systemMenuService.update(systemMenuEntity);
                return returnAjax(true, "保存成功", null, null);
            } catch (Exception e) {
                logger.error("修改基础数据异常", e);
                return returnAjax(false, "修改基础数据异常", null, null);
            }
        } else {
            logger.error("修改基础数据，参数不存在或者参数存在id");
            return returnAjax(false, "参数异常", null, null);
        }
    }

    /**
     * 批量更新状态
     *
     * @param ids    id字符串， 1,2,3,...形式
     * @param status 状态代码
     * @return
     */
    @RequestMapping(value = "/batch_update_state.do", method = RequestMethod.POST)
    @ResponseBody
    public Map delRows(String ids, String status) {
        try {
            systemMenuService.batchUpdateState(ids, status);
            return returnAjax(true, "更新成功", null, null);
        } catch (Exception e) {
            logger.error("批量更新异常", e);
        }
        return returnAjax(false, "批量更新异常", null, null);
    }

}


