/**
 * Created by Administrator on 2017/7/18.
 */
"use strict";

var bootTableSelector = "#bootTable";
var bootTableSearchSelector = "#bootTableSearch";
var bootTableToolSelector = "#bootTableTool";
var bootTable = {};
bootTable.option = {
    url : basePath + "/admin/role/page_data.json",
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
            field : "systemId",
            title : "业务系统编号",
            align: 'center'
        },
        {
            field : "name",
            title : "角色名称",
            align: 'center'
        },
        {
            field : "nameKey",
            title : "角色键名",
            align: 'center'
        },
        {
            field : "validity",
            title : "角色有效期",
            align: 'center'
        },
        {
            field : "arights",
            title : "菜单访问权限",
            align: 'center'
        },
        {
            field : "hrights",
            title : "菜单操作权限",
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
        }
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