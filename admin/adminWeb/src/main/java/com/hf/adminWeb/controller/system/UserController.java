package com.hf.adminWeb.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.hf.adminDao.entity.UserEntity;
import com.hf.adminService.service.UserService;
import com.hf.common.base.BaseController;
import com.hf.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户controller
 * Created by 韩峰 on 2016/8/15.
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private ApplicationContext applicationContext;

    // /**
    //  * 用户列表 list json到页面
    //  * @return
    //  */
    // @RequestMapping(value = "all_list")
    // public String toAllList(){
    //     for (int i=0;i<=30;i++){
    //         User user = new User();
    //         user.setName("test"+i);
    //         user.setStatus(1);
    //         user.setEmail("213213213@qq.com");
    //         user.setLoginNum("test"+i+i);
    //         user.setLoginPwd("test");
    //         user.setPhone(13438336891L);
    //         user.setCreateId(1L);
    //         user.setCreateName("超级管理员");
    //         user.setCreateDate(new Date());
    //         user.setUpdateDate(new Date());
    //         user.setUpdateId(1L);
    //         user.setUpdateName("超级管理员");
    //         userService.save(user);
    //     }
    //     return "system/user/all_list";
    // }

    /**
     * 用户列表
     * bootstrap table server方式
     * bootstrap table调用toPageListJson生成json传回页面
     *
     * @return {String} 页面路径
     */
    // @FormToken(needSaveToken = true)
    @RequestMapping(value = "/page.htm", method = RequestMethod.GET)
    // @PreAuthorize("hasAnyRole('admin', 'user')")
    public String toPageList(String name, String id) {
        logger.debug("访问列表参数输出： id:" + id + "   ,name:" + name);
        return "system/user/list";
    }

    /**
     * 用户列表,返回json(包含分页查询)
     *
     * @param pageSize   分页大小
     * @param pageNumber 当前页码
     * @param userEntity 查询参数
     * @return {Map} 返回Map结果
     */
    @RequestMapping(value = "page_data.json", method = RequestMethod.POST)
    @ResponseBody
    public Map toPageListJson(int pageSize, int pageNumber, UserEntity userEntity) throws BaseException {

        // UserExtendSecurity userDetails
        //         = (UserExtendSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PageInfo<UserEntity> page = userService.findAllPageList(pageNumber, pageSize,userEntity);
        return returnBootTable(true, "查询成功", page);
    }

    /**
     * 跳转到添加页
     *
     * @return {String} 页面路径
     */
    @RequestMapping(value = "to_add.htm", method = RequestMethod.GET)
    public String toAddUser(Model model) {
        UserEntity user = (UserEntity) createObjFromClass(UserEntity.class);
        model.addAttribute("dataEntity",JSON.toJSONString(user, SerializerFeature.WriteMapNullValue));
        return "system/user/form";
    }


    /**
     * 添加
     *
     * @param user 用户实体
     * @return {Map} 返回Map结果
     */
    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public Map addUser(UserEntity user) {
        if (user != null && StringUtils.isBlank(user.getId())){
            try {
                // 加密密码
                String textPwd = user.getLoginPwd();
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(textPwd);
                user.setLoginPwd(hashedPassword);
                // TODO
                // BaseEntity.setCreateAndUpdateUser(user);
                userService.save(user);
            } catch (Exception e) {
                logger.error("注册用户异常",e);
                return returnAjax(false,e.getMessage(),null,null);
            }
            return returnAjax(true,"保存成功",null,null);
        } else {
            logger.error("注册用户，参数不存在或者参数存在id");
            return returnAjax(false,"参数异常",null,null);
        }
    }

    /**
     * 跳转到更新页
     *
     * @return {String} 页面路径
     */
    @RequestMapping(value = "to_update.htm", method = RequestMethod.GET)
    public String toUpdateUser(UserEntity user,Model model) throws BaseException {
        UserEntity userEntity = userService.findOne(user);
        model.addAttribute("dataEntity", JSON.toJSONString(userEntity));
        return "system/user/form";
    }

    /**
     * 更新
     *
     * @param user 用户实体
     * @return {Map} 返回Map结果
     */
    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    public Map updateUser(UserEntity user) throws BaseException {

        if (user != null && StringUtils.isNotBlank(user.getId())){
            // try {
            // TODO: 2017/10/7
            //     BaseEntity.setUpdateUser(user);
                userService.update(user);
            // } catch (Exception e) {
            //     logger.error("修改用户基础数据异常",e);
            //     return returnAjax(false,"修改用户基础数据异常",null,null);
            // }
            return returnAjax(true,"保存成功",null,null);
        } else {
            logger.error("修改用户基础数据，参数不存在或者参数存在id");
            return returnAjax(false,"参数异常",null,null);
        }
    }

    /**
     * 批量状态更新
     * @param ids ids
     * @return {Map} 返回Map结果
     */
    @RequestMapping(value = "batch_update_state.do", method = RequestMethod.POST)
    @ResponseBody
    public Map delUser(String ids,String status) {
        try {
            userService.batchUpdateState(ids,status);
            return returnAjax(true,"更新成功",null,null);
        } catch (Exception e) {
            logger.error("批量更新异常",e);
        }
        return returnAjax(false,"批量更新异常",null,null);
    }

}
