<#assign configFtlPageRequestMapping>
    <#if fullConfig["controller.requestMapping"]??>${fullConfig["controller.requestMapping"] ?trim}</#if>
</#assign>
/**
 * Created by Administrator on 2017/7/18.
 */
"use strict";

var bootTableSelector = "#bootTable";
var bootTableSearchSelector = "#bootTableSearch";
var bootTableToolSelector = "#bootTableTool";
var bootTable = {};
bootTable.option = {
    url : basePath + "${configFtlPageRequestMapping?trim}/page_data.json",
    toolbar : bootTableToolSelector, /*自定义的toolbar*/
    columns : [
        <#--如果data.fields 存在,则循环写入变量-->
        <#if data.fieldModels??>
            <#list data.fieldModels as fildModels>
            <#if fildModels.lowerCamelCaseName == "id">
            {
                idField: 'id',
                checkbox: true,
                // clickToSelect : true,/*点击选中行*/
                // formatter : function(value,row,index){},
                // events : function(event,value,row,index){/*the jQuery event. */},
                align: 'center'
            },
            <#elseif fildModels.lowerCamelCaseName == "status">
            {
                field : "status",
                title : "状态",
                formatter : BSTStatusformatter ,
                align: 'center'
            },
            <#else>
            {
                field : "${fildModels.lowerCamelCaseName}",
                title : "${fildModels.comment}",
                align: 'center'
            },
            </#if>
            </#list>
        </#if>
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