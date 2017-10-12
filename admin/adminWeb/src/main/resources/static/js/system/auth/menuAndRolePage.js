/**
 * 菜单角色关联js
 * Created by Administrator on 2017/8/9.
 */
"use strict";
var treeObj; /*zTree 实例*/
var treeMenuObj; /*菜单实例*/
$(document).ready(function(){
    treeObj = ZTree.createTree(treeSelect, setting, zNodes);
    treeMenuObj = createMenuTree(menuEntityList);
});

function batchAuthMenu() {
    // 获取角色树勾选的节点
    var roleNodes = treeObj.getCheckedNodes(true);
    // 获取用户列表选择项
    // var menus = $(bootTableSelector).bootstrapTable("getSelections");
    var menus = treeMenuObj.getCheckedNodes(true);
    if (roleNodes === null || roleNodes.length <= 0){
        Toast.showWarn("请勾选角色");
        return false;
    }
    if (menus === null || menus.length <= 0){
        Toast.showWarn("请勾选菜单");
        return false;
    }
    var roleIds = [];
    var menuIds = [];
    for (var i1 in roleNodes) {
        var roleNode = roleNodes[i1];
        roleIds.push(roleNode.id);
    }
    for (var i2 in menus) {
        var user = menus[i2];
        menuIds.push(user.id);
    }
    batchAuthMenuAjax(menuIds.join(","),roleIds.join(","));
}
function batchAuthMenuAjax(menuIdStr,roleIdStr) {
    ajaxWarp({
        url : basePath + "/admin/system/auth/menu_role/batch_auth.do",
        data : {
            menuIds : menuIdStr,
            roleIds : roleIdStr,
        }
    })
}
//------------------ 分割线 - 自定义功能  end  ------------------

/* ----- start zTree -----*/
var treeSelectName = "treeDemo";
var treeSelect = "#" + treeSelectName;
var setting = {
    check: {
        enable: true
    },
    edit : {
      enable :false,
    },
    callback: {
        beforeClick : function() {
          return true;
        },
        onClick: zTreeOnClick
    }
};
/*将页面上的参数赋值*/
var zNodes = roleEntityList;



function zTreeOnClick(event, treeId, treeNode, clickFlage) {
    ajaxWarp({
        sync : true,
        url : basePath + "/admin/role/getSingleRole.do",
        data : {
            id : treeNode.id,
            status :1
        },
        success : function (result) {
            if (result.status === true && result.data) {
                // 对应的role的菜单树选中操作
                treeMenuObj = createMenuTree(menuEntityList);
                var idsStr = result.data.arights;
                if (idsStr) {
                    var ids = idsStr.split(",");
                    for (var i=0,s=ids.length;i<s;i++) {
                        var id = ids[i];
                        var node = treeMenuObj.getNodeByParam("id", id , null);
                        if (node) {
                            treeMenuObj.checkNode(node, true);
                        }
                    }
                }
            } else {
                Toast.showError(data.info);
            }
        }
    });
}
/* -----  end  zTree -----*/

/*----- start zTree menu  -----*/


function createMenuTree(zNodesMenu) {
    /*获取树*/
    var treeMenuSelectName = "treeMenu";
    var treeMenuSelect = "#" + treeMenuSelectName;

    var menuSetting = {
        check: {
            enable: true
        },
        data: {
            key: {
                name: "menuName"
            },
            simpleData: {
                idKey: "id",
                pIdKey: "proMenuId",
                rootPId: 1
            }
        },
        callback: {
            onCheck: function(){}
        }
    };

    return ZTree.createTree(treeMenuSelect, menuSetting, zNodesMenu);
}
/**
 * 选中复选框回调函数
 * @param treeId
 * @param treeNode
 */
function zTreeOnCheck(event, treeId, treeNode) {

    // 获取角色树勾选的节点
    var roleNodes = treeObj.getCheckedNodes(true);
    if (roleNodes === null || roleNodes.length <= 0){
        Toast.showWarn("请勾选角色");
        return false;
    }
    var roleIds = [];
    for (var i1 in roleNodes) {
        var roleNode = roleNodes[i1];
        roleIds.push(roleNode.id);
    }

    var checked = treeNode.checked;
    var status ; // 1 启用 0删除
    if (checked === true || checked === "checked"){
        status = 1;
    } else {
        status = 0;
    }
    // 取消或者添加角色关联的菜单
    ajaxWarp({
        sync : true,
        url : basePath + "/admin/system/auth/auth_role.do",
        data :{
            roleId : roleIds.join(","),
            menuId : treeNode.id,
            status : status
        }
    })
}
/*-----  end  zTree menu  -----*/
