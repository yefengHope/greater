package com.hf.adminWeb.controller.system;

import com.alibaba.fastjson.JSON;
import com.hf.adminDao.entity.RoleEntity;
import com.hf.adminDao.entity.SystemMenuEntity;
import com.hf.adminDao.entity.UserAndRoleEntity;
import com.hf.adminDao.entity.UserEntity;
import com.hf.adminService.service.*;
import com.hf.common.base.BaseController;
import com.hf.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 后台管理 -- 授权管理
 * Created by HF on 2017/8/9.
 */
@Controller
@RequestMapping(value = "/admin/system/auth")
public class UserAndRoleController extends BaseController {

    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(UserAndRoleController.class);
    }

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private SystemMenuService systemMenuService;

    @Resource
    private UserAndRoleService userAndRoleService;

    @Resource
    private UserAndRoleExtendService userAndRoleExtendService;

    @Resource
    private MenuAndRoleExtendService menuAndRoleExtendService;

    /**
     * 跳转到用户角色关联页面
     */
    @RequestMapping(value = "/user_role/page.htm")
    public String toUserAndRoleHtml(RoleEntity roleEntity, Model model) throws BaseException {
        roleEntity.setStatus(1);
        List<RoleEntity> roles = roleService.findAllList(roleEntity);
        model.addAttribute("roleEntityList", JSON.toJSONString(roles));
        return "system/auth/userAndRolePage";
    }

    /**
     * 跳转到角色菜单关联页面
     */
    @RequestMapping(value = "/role_menu/page.htm")
    public String toRoleAndMenuHtml(RoleEntity roleEntity ,Model model) throws BaseException {
        roleEntity.setStatus(1);
        List<RoleEntity> roles = roleService.findAllList(roleEntity);
        model.addAttribute("roleEntityList", JSON.toJSONString(roles));

        SystemMenuEntity menuEntity = new SystemMenuEntity();
        menuEntity.setStatus(1);
        List<SystemMenuEntity> menus = systemMenuService.findAllList(menuEntity);
        model.addAttribute("menuEntityList", JSON.toJSONString(menus));
        return "system/auth/menuAndRolePage";
    }

    //------------------ 分割线 - 用户角色 功能  ------------------

    /**
     * 批量授权用户
     */
    @RequestMapping(value = "/user_role/batch_auth.do")
    @ResponseBody
    public Map batchAuthUser( String userIds,  String roleIds) {
        try {
            if (StringUtils.isNotBlank(userIds) && StringUtils.isNotBlank(roleIds)) {
                userAndRoleExtendService.batchRelateUserAndRole(userIds.split(","),roleIds.split(","));
                return returnAjax(true,"授权成功",null,null);
            } else {
                return returnAjax(false,"授权失败！请勾选角色或用户！" ,null,null);
            }
        } catch (Exception e) {
            logger.error("批量授权异常",e);
            return returnAjax(false,"授权失败！请重试!" + e.getMessage(),null,null);
        }
    }

    /**
     * 单用户权限页面
     * @param id    用户id
     */
    @RequestMapping(value = "/user_role/single_user.htm")
    public String toSingleUserHtml(String id,Model model) {
        String userId = id;
        if (StringUtils.isNotBlank(userId)) {
            try {
                // 查询有效用户
                UserEntity userEntity = new UserEntity();
                userEntity.setId(userId);
                userEntity.setStatus(1);
                UserEntity userEntityDb = userService.findOne(userEntity);
                if (null != userEntityDb) {

                    model.addAttribute("userEntity",userEntityDb);

                    // 查询当前用户绑定角色
                    UserAndRoleEntity userAndRoleEntity = new UserAndRoleEntity();
                    userAndRoleEntity.setStatus(1);
                    userAndRoleEntity.setUserId(userId);
                    List<UserAndRoleEntity> userAndRoleEntities
                            = userAndRoleService.findAllList(userAndRoleEntity);

                    model.addAttribute("userAndRoleEntities",userAndRoleEntities);

                    // 查询所有有效角色
                    RoleEntity roleEntity = new RoleEntity();
                    roleEntity.setStatus(1);
                    List<RoleEntity> roles = roleService.findAllList(roleEntity);

                    model.addAttribute("roleEntityList", roles);
                } else {
                    model.addAttribute("tipInfo",returnAjax(false,"无当前用户，或用户无效",null,null));
                }
            } catch (Exception e) {
                logger.error("【单用户权限页面】查询数据异常",e);
                model.addAttribute("tipInfo",returnAjax(false,"查询数据异常，请重试",null,null));
            }
        } else {
            model.addAttribute("tipInfo",returnAjax(false,"请勾选一个用户",null,null));
        }
        return "system/auth/singleUserAuth";
    }

    /**
     * 用户授权
     */
    @RequestMapping(value = "/auth_user.do")
    @ResponseBody
    public Map authUser(String userId,Long roleId) {
        if (StringUtils.isNotBlank(userId) && null != roleId) {
            UserAndRoleEntity userAndRoleEntity = new UserAndRoleEntity();
            userAndRoleEntity.setUserId(userId);
            userAndRoleEntity.setRoleId(roleId);
            try {
                UserAndRoleEntity userAndRoleEntityDb = userAndRoleService.findOne(userAndRoleEntity);
                if (1 != userAndRoleEntityDb.getStatus()) {
                    userAndRoleEntityDb.setStatus(1);
                } else {
                    userAndRoleEntityDb.setStatus(2);
                }
                userAndRoleService.update(userAndRoleEntityDb);
                return returnAjax(true,"授权成功！",null,null);
            } catch (Exception e) {
                logger.error("【用户授权】",e);
                return returnAjax(false,"授权查询异常！",null,null);
            }
        } else {
            return returnAjax(false,"参数缺失！",null,null);
        }
    }


    //------------------ 分割线 - 角色菜单 功能  ------------------

    /**
     * 批量授权角色菜单
     */
    @RequestMapping(value = "/menu_role/batch_auth.do")
    @ResponseBody
    public Map batchAuthMenu( String menuIds,  String roleIds) {
        try {
            if (StringUtils.isNotBlank(menuIds) && StringUtils.isNotBlank(roleIds)) {
                menuAndRoleExtendService.batchRelateMenuAndRole(menuIds,roleIds.split(","));
                return returnAjax(true,"授权成功",null,null);
            } else {
                return returnAjax(false,"授权失败！请勾选角色或菜单！" ,null,null);
            }
        } catch (Exception e) {
            logger.error("批量授权异常",e);
            return returnAjax(false,"授权失败！请重试!" + e.getMessage(),null,null);
        }
    }

    /**
     * 角色菜单授权
     */
    @RequestMapping(value = "/auth_role.do")
    @ResponseBody
    public Map authMenu(String menuId,String roleId,int status) {
        if (StringUtils.isNotBlank(menuId) && null != roleId) {
            try {
                menuAndRoleExtendService.batchRelateMenuAndRole(menuId, new String[]{roleId},status);
                return returnAjax(true,"授权成功！",null,null);
            } catch (Exception e) {
                logger.error("【角色菜单授权】",e);
                return returnAjax(false,"授权异常！",null,null);
            }
        } else {
            return returnAjax(false,"参数缺失！",null,null);
        }
    }
}
