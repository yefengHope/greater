/**
 * 用户角色关联js
 * Created by Administrator on 2017/8/9.
 */
"use strict";


function batchAuthUser() {
    // 获取角色树勾选的节点
    var roleNodes = treeObj.getCheckedNodes(true);
    // 获取用户列表选择项
    var users = $(bootTableSelector).bootstrapTable("getSelections");
    if (roleNodes === null || roleNodes.length <= 0){
        Toast.showWarn("请勾选角色");
        return false;
    }
    if (users === null || users.length <= 0){
        Toast.showWarn("请勾选用户");
        return false;
    }
    var roleIds = [];
    var userIds = [];
    for (var i1 in roleNodes) {
        var roleNode = roleNodes[i1];
        roleIds.push(roleNode.id);
    }
    for (var i2 in users) {
        var user = users[i2];
        userIds.push(user.id);
    }
    ajaxWarp({
        url : basePath + "/admin/system/auth/user_role/batch_auth.do",
        data : {
            userIds : userIds.join(","),
            roleIds : roleIds.join(","),
        }
    })
}
//------------------ 分割线 - 自定义功能  end  ------------------

/* ----- start zTree -----*/
var treeSelectName = "treeDemo";
var treeSelect = "#" + treeSelectName;
var setting = {
    view: {
        selectedMulti: false
    },
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true
        }
    },
};
/*将页面上的参数赋值*/
var zNodes = roleEntityList;

var treeObj; /*zTree 实例*/
$(document).ready(function(){
    treeObj = $.fn.zTree.init($(treeSelect), setting, zNodes);
});

var newCount = 1;
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        var zTree = $.fn.zTree.getZTreeObj(treeSelectName);
        zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
        return false;
    });
}
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
}

/* -----  end  zTree -----*/

/* ----- start bootstrap table -----*/
var bootTableSelector = "#bootTable";
var bootTableSearchSelector = "#bootTableSearch";
var bootTableToolSelector = "#bootTableTool";
var bootTable = {};
bootTable.option = {
    url : basePath + "/admin/user/page_data.json",
    toolbar : bootTableToolSelector, /*自定义的toolbar*/
    columns : [
        {
            idField: 'id',
            checkbox: true,
            // clickToSelect : true,/*点击选中行*/
            // formatter : function(value,row,index){},
            // events : function(event,value,row,index){/*the jQuery event. */},
            align: 'center'
        },
        {
            field : "loginNum",
            title : "登录账号",
            align: 'center'
        },
        {
            field : "name",
            title : "账号昵称",
            align: 'center'
        },
        {
            field : "phone",
            title : "手机号码",
            align: 'center'
        },
        {
            field : "email",
            title : "email",
            align: 'center'
        },
        {
            field : "status",
            title : "账号状态",
            formatter : BSTStatusformatter ,
            align: 'center'
        },
    ]
};
//table init
bootTable.init = function () {
    $(bootTableSelector).myBootstrapTable(bootTable.option);
};

$(function () {
    bootTable.init();
});

function searchCondition() {
    $(bootTableSelector).bootstrapTable("refresh",{
        query: $(bootTableSearchSelector).getBSTSearchParams(),
    });
}
/* -----  end  bootstrap table -----*/