<#assign configRequestMapping>
    <#if fullConfig["controller.requestMapping"]??>${fullConfig["controller.requestMapping"] ?trim}</#if>
</#assign>
<#assign configPrefix>
    <#if fullConfig["controller.impl.ftl.prefix"]??>${fullConfig["controller.impl.ftl.prefix"] ?trim}</#if>
</#assign>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品列表</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    ${r'<%@include file="/WEB-INF/jsp/common/include/title.jsp" %>'}
    <link rel="stylesheet" href="${r'${ctx}'}/public/libs/lib/css/bootstrap-table.css">
    <link href="${r'${ctx}'}/public/libs/lib/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
    <link href="${r'${ctx}'}/public/libs/bootstrap/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet"/>
</head>

<body class="gray-bg">
<input type="hidden" id="csrfToken" name="${r'${_csrf.parameterName}'}" value="${r'${_csrf.token}'}"/>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="col-sm-12">
            <!-- Example Events -->
            <div class="example-wrap">
                <div>
                    <div>
                        <form id="bootTableSearch" class="form-inline">
                            <div class="form-group">
                            <#--<label for="name">Name</label>-->
                                <input type="text" class="form-control" name="" placeholder="二货这里你要手写">
                            </div>
                        <#--如果data.fields 存在,则循环写入变量-->
                        <#if data.fieldModels??>
                            <#list data.fieldModels as fildModels>
                                    <div class="form-group">
                                    <#--<label for="name">Name</label>-->
                                        <input type="text" class="form-control" name="${fildModels.lowerCamelCaseName}" placeholder="查询${fildModels.comment}">
                                    </div>
                            </#list>
                        </#if>
                            <div class="form-group">
                                <select name="status" placeholder="查询状态" class="form-control ">
                                    <option value="">查询状态</option>
                                    <option value="0">删除</option>
                                    <option value="1">启用</option>
                                    <option value="2">禁用</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <input type="button" class="form-control btn-outline btn-primary" value="查询"
                                       onclick="searchCondition()"/>
                            </div>
                            <div class="form-group">
                                <input type="button" class="form-control btn-outline btn-default" value="清空"
                                       onclick="clearSearchForm('#bootTableSearch')"/>
                            </div>
                        </form>
                    </div>
                    <div class="btn-group hidden-xs" id="bootTableTool" role="group">
                        <button type="button" class="btn btn-outline btn-default"
                                onclick="BootstrapTableFunc.toAddForm('#bootTable','${r'${ctx}}'}${configRequestMapping?trim}/add','role_1','新增角色',null)">
                            <i class="glyphicon glyphicon-plus" aria-hidden="true">新增</i>
                        </button>
                        <button type="button" class="btn btn-outline btn-default"
                                onclick="BootstrapTableFunc.toEditForm('#bootTable','${r'${ctx}}'}${configRequestMapping?trim}/update','role_2','编辑角色')">
                            <i class="glyphicon glyphicon-edit" aria-hidden="true">编辑</i>
                        </button>
                        <div class="btn-group">
                            <button data-toggle="dropdown" class="btn btn-outline btn-default dropdown-toggle" aria-expanded="false">
                                <i class="glyphicon glyphicon-list-alt" aria-hidden="true">状态</i>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="javascript:void(0)"
                                       onclick="BootstrapTableFunc.updateRows('#bootTable','${r'${ctx}}'}${configRequestMapping?trim}/updateState',1)">启用</a>
                                </li>
                                <li><a href="javascript:void(0)"
                                       onclick="BootstrapTableFunc.updateRows('#bootTable','${r'${ctx}}'}${configRequestMapping?trim}/updateState',2)">禁用</a>
                                </li>
                                <li><a href="javascript:void(0)"
                                       onclick="BootstrapTableFunc.updateRows('#bootTable','${r'${ctx}}'}${configRequestMapping?trim}/updateState',0)">删除</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="example">
                    <table id="bootTable">
                    </table>
                </div>
            </div>
            <!-- End Example Events -->
        </div>
    </div>
</div>
</div>
<!-- End Panel Other -->
</div>
${r'<%@include file="/WEB-INF/jsp/common/include/footer.jsp" %>'}
<script src="${r'${ctx}'}/public/js/utils/bootstrap.extends.js"></script>
<script src="${r'${ctx}'}/public/libs/lib/js/bootstrap-treeview.js"></script>
<script src="${r'${ctx}'}/public/libs/lib/js/bootstrap-datetimepicker.min.js"></script>
<script src="${r'${ctx}'}/public/libs/lib/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${r'${ctx}'}/public/libs/bootstrap/bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="${r'${ctx}'}/public/libs/bootstrap/bootstrap-select/js/i18n/defaults-zh_CN.min.js"></script>
<script src="${r'${ctx}'}/public/libs/lib/js/bootstrap-table-zh-CN.js"></script>
<script src="${r'${ctx}'}/public/libs/lib/js/tableExport.js"></script>
<script src="${r'${ctx}'}/public/libs/lib/js/bootstrap-table-export.js"></script>

<script src="${r'${ctx}'}/public/${configPrefix?trim}/list.js"></script>
</body>
</html>