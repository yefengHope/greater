/**
 * Created by Administrator on 2017/7/18.
 */
"use strict";

var bootTableSelector = "#bootTable";
var bootTableSearchSelector = "#bootTableSearch";
var bootTableToolSelector = "#bootTableTool";
var bootTable = {};
bootTable.option = {
    url : basePath + "/admin/system/menu/page_data.json",
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
                field : "menuType",
                title : "菜单类型",
                align: 'center'
            },
            {
                field : "proMenuId",
                title : "父级菜单",
                align: 'center'
            },
            {
                field : "menuLevels",
                title : "菜单层级",
                align: 'center'
            },
            {
                field : "menuName",
                title : "菜单名字",
                align: 'center'
            },
            {
                field : "menuAddress",
                title : "菜单地址",
                align: 'center'
            },
            {
                field : "sort",
                title : "序号",
                align: 'center'
            },
            {
                field : "status",
                title : "状态",
                formatter : BSTStatusformatter ,
                align: 'center'
            },
            {
                field : "updateName",
                title : "更新人名称",
                align: 'center'
            },
            {
                field : "updateDate",
                title : "更新时间",
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