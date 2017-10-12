/**
 * 树形菜单
 * Created by Administrator on 2017/8/16.
 */
"use strict";
/* ----- start zTree -----*/
var treeSelectName = "treeDemo";
var treeSelect = "#" + treeSelectName;
var setting = {
    view: {
        addHoverDom: function (treeId, treeNode) {
            addHoverZTreeDom(treeId, treeNode,function(treeId,treeNode){
                // 弹出表单
                formVue.$data.id = null;
                formVue.$data.menuType = treeNode.menuType;    // 默认为点击当前树的菜单类型
                formVue.$data.proMenuId = treeNode.id;         // 默认父级id
                formVue.$data.menuLevels = null;
                formVue.$data.menuName=null,
                    formVue.$data.menuAddress=null,
                    formVue.$data.sort=1,
                    formVue.$data.status=1,
                    $(formSelect).data("treeNode",treeNode);  // 保存成功后添加节点会回调，添加到树
                $(formSelect).bootstrapValidator("resetForm"); // 清除校验样式
                $(formSelect).show();
            });
        },
        selectedMulti: false
    },
    check: {
        enable: true
    },
    data: {
        key: {
            name: "menuName"
        },
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "proMenuId",
            rootPId: 1
        }
    },
    edit: {
        enable: true,
        renameTitle: "编辑菜单",
        removeTitle: "删除菜单"
    },
    callback: {
        beforeEditName: zTreeBeforeEditName,
        beforeRemove: zTreeBeforeRemove,
        onMouseUp: function (event, treeId, treeNode) {
            // console.info(event);
            // console.info(treeId);
            // console.info(treeNode);
        }
    }
};
/*将页面上的参数赋值*/
var zNodes = menuEntityList;

var treeObj; /*zTree 实例*/
$(document).ready(function(){
    treeObj = ZTree.createTree(treeSelect,setting,zNodes);
});

// 添加节点
function addNodeWrap(treeSelectName,treeNode,id) {
    var menuName = $("#rightForm input[name=menuName]").val();
    treeObj.addNodes(treeNode, {id:id, proMenuId:treeNode.id, menuName:menuName},true);
}

function zTreeBeforeRemove(treeId, treeNode) {
    // 如果返回 false，zTree 将不删除节点，也无法触发 onRemove 事件回调函数
    if (treeNode.id === 1 || treeNode.id === 2){
        Toast.showWarn("根节点不能删除");
        return false;
    }
    if (confirm("确认删除 节点 -- " + treeNode.menuName + " 吗？")) {
        // 发起删除
        ajaxWarp({
            sync : true,
            url : basePath + "/admin/system/menu/batch_update_state.do",
            data : {
                ids : treeNode.id,
                status : 0,
            },
            success: function (data,status,xhr) {
                if (data.status === true) {
                    Toast.showSuccess("删除成功！");
                    return true;
                } else {
                    Toast.showError(data.info);
                    return false;
                }
            },
            error: function (data,status,error) {
                var info = data.info;
                if (info) {
                    Toast.showError(info);
                }
                return false;
            }
        })
    } else {
        return false;
    }


}
function zTreeBeforeEditName(treeId, treeNode) {

    formVue.$data.id      =treeNode.id,
        formVue.$data.menuType=treeNode.menuType,
        formVue.$data.proMenuId=treeNode.proMenuId,
        formVue.$data.menuLevels=treeNode.menuLevels,
        formVue.$data.menuName=treeNode.menuName,
        formVue.$data.menuAddress=treeNode.menuAddress,
        formVue.$data.sort=treeNode.sort,
        formVue.$data.status=treeNode.status,

        $(formSelect).bootstrapValidator("resetForm"); // 清除校验样式
    $(formSelect).show();
    return false;
}

/* -----  end  zTree -----*/
