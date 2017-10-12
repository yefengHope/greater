/**
 * Created by Administrator on 2017/7/18.
 */
"use strict";

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
        // {
        //     field : "loginPwd",
        //     title : "登录密码",
        //     width : "15",
        //     align: 'center'
        // },
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
        {
            field : "headIcon",
            title : "账号头像",
            align: 'center'
        },
        // {
        //     field : "updateId",
        //     title : "更新id",
        //     align: 'center'
        // },
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