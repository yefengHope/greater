/**
 * ztree 扩展封装
 * Created by Administrator on 2017/8/18.
 */
"use strict";
var ZTree = {};

ZTree.defSetting = {
    view: {
        // addHoverDom: function(treeId, treeNode) {
        //     addHoverZTreeDom(treeId, treeNode,function(treeId,treeNode){
        //
        //     })
        // },
        removeHoverDom: removeHoverZTreeDom,
        selectedMulti: false
    },
    check: {
        enable: true,
        autoCheckTrigger: true
    },
    data: {
        // key: {name: "menuName"},
        simpleData: {
            enable: true,
            // idKey: "id",
            // pIdKey: "proMenuId",
            // rootPId: 1
        }
    },
    edit: {
        enable: true,
        renameTitle: "编辑",
        removeTitle: "删除"
    },
    // callback: {
        // beforeEditName: zTreeBeforeEditName,
        // beforeRemove: zTreeBeforeRemove,
        // onMouseUp: function (event, treeId, treeNode) {
        //     // console.info(event);
        //     // console.info(treeId);
        //     // console.info(treeNode);
        // }
    // }
};


/**
 * 鼠标悬浮zTree列表事件，显示的自定义添加框
 * @param treeId
 * @param treeNode
 * @param callback  回调函数，需要在函数里自己添加节点操作，
 *  如 ： treeObj.addNodes(treeNode, {id:id, pId:treeNode.id, name:menuName},true);
 */
function addHoverZTreeDom(treeId, treeNode,callback) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='添加菜单' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        callback(treeId, treeNode);
        return false;
    });
}
/**
 * 鼠标离开zTree列表事件
 * @param treeId
 * @param treeNode
 */
function removeHoverZTreeDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
}
/**
 * 初始化树
 * @param selector 树选择器
 * @param opt      自定义选项
 * @param zNodes   节点数据
 */
ZTree.createTree = function(selector ,opt, zNodes) {
    var o = JSON.parse(JSON.stringify(ZTree.defSetting));
    $(selector).empty();
    o = $.extend(true,o,opt);
    return $.fn.zTree.init($(selector), o, zNodes);
};
