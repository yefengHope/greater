package com.hf.adminWeb.controller.system;

import com.alibaba.fastjson.JSON;
import com.hf.adminDao.entity.SystemMenuEntity;
import com.hf.adminDao.entity.UserExtendSecurity;
import com.hf.adminWeb.util.CommonUtils;
import com.hf.adminWeb.vo.Tree;
import com.hf.common.base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by HanFeng on 2017/7/28.
 */
@Controller
@RequestMapping(value = "/admin")
public class IndexAdminController extends BaseController {

    @RequestMapping(value = "/index.htm")
    public String index(Model model){
        Authentication authentication = CommonUtils.getAuthenticationBySecurity();
        UserExtendSecurity userExtendSecurity = (UserExtendSecurity) authentication.getPrincipal();
        model.addAttribute("user",userExtendSecurity);
        model.addAttribute("role", StringUtils.join(authentication.getAuthorities(),","));
        List<SystemMenuEntity> menus = userExtendSecurity.getMenuList();
        Map<String,Tree> map = new HashMap<>();
        menus.forEach(r -> {
            Tree<SystemMenuEntity> tree = new Tree<>();
            tree.setId(String.valueOf(r.getId()));
            tree.setPid(String.valueOf(r.getProMenuId()));
            tree.setT(r);
            Integer sort = null;
            try {
                sort = Integer.valueOf(r.getSort());
                if (sort == null ) {
                    sort = 1;
                }
            } catch (NumberFormatException e) {
                sort = 1;
                e.printStackTrace();
            }
            tree.setSort(sort);
            tree.setChildren(new ArrayList<>());
            map.put(String.valueOf(r.getId()),tree);
        });

        map.values().forEach (r -> {
            String pid = r.getId();
            List<Tree> children = r.getChildren();
            for(Tree tree : map.values()){
                String myPid = tree.getPid();
               if (pid.equals(myPid)) {
                   children.add(tree);
               }
            }
            if (children.size() > 0) {
                Collections.sort(children);
            }
        });

        List<Tree> menuList = new ArrayList<>();
        map.values().forEach(r->{
            List<Tree> children = r.getChildren();
           if (children.size() > 0) {
               menuList.add(r);
           }
        });
        Collections.sort(menuList);
        model.addAttribute("menus",menuList);
        System.out.println(JSON.toJSONString(menuList));
        return "index.admin";
        // return "test.index.admin";
    }
/*
    深度树
    循环列表
        获取pid
            if pid = id add child

 */

    // public void buildTree(List<SystemMenuEntity> list,String pid,List<Tree<SystemMenuEntity>> trees ) {
    //     list.forEach(r -> {
    //         if (pid.equals(String.valueOf(r.getProMenuId()))) {
    //             Tree<SystemMenuEntity> tree = new Tree<>();
    //             tree.setId(String.valueOf(r.getId()));
    //             tree.setPid(String.valueOf(r.getProMenuId()));
    //             tree.setChildren(new ArrayList<>());
    //             trees.add(tree);
    //         }
    //     });
    // }

    @RequestMapping(value = "/index.do")
    @ResponseBody
    public Map indexJson(Model model){
        System.out.println();
        return returnAjax(true,"登录成功",null,null);
        // return "test.index.admin";
    }

}
