/**
 * Created by Administrator on 2017/7/18.
 */
"use strict";

var bootTableSelector = "#bootTable";
var bootTableSearchSelector = "#bootTableSearch";
var bootTableToolSelector = "#bootTableTool";
var bootTable = {};
bootTable.option = {
    url : basePath + "/admin/system/setting/page_data.json",
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
                field : "keyType",
                title : "键类型",
                align: 'center'
            },
            {
                field : "keyName",
                title : "键名",
                align: 'center'
            },
            {
                field : "keyValue",
                title : "键值",
                align: 'center'
            },
            {
                field : "keyRemark",
                title : "键备注",
                align: 'center'
            },
        {
            field : "sort",
            title : "序号",
            align: 'center'
        },
        {
            field : "status",
            title : "账号状态",
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