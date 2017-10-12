<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>用户列表 - Bootstrap Table</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
<#include "../../commom/include.hplus.css.ftl" />
<#include "../../commom/include.hplus.list.css.ftl" />
    <#--<link href="/plugs/bootstrap/bootstrap-select-1.11.2/dist/css/bootstrap-select.min.css"  rel="stylesheet" type="text/css" />-->
    <base target="_blank">
</head>

<body class="gray-bg">
<input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="col-sm-12">
        <!-- Example Events -->
        <div class="example-wrap"> <#--example-wrap-->
            <div >
                <div>
                    <form id="bootTableSearch" class="form-inline">
                        <div class="form-group">
                        <#--<label for="name">Name</label>-->
                            <input type="text" class="form-control" name="name" placeholder="查询昵称">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="loginNum" placeholder="查询帐号">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="phone" placeholder="查询手机号">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="email" placeholder="查询Email">
                        </div>
                        <div class="form-group">
                            <select name="status" placeholder="查询状态" class="form-control">
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
                <div id="bootTableTool" class="btn-group hidden-xs" role="group">
                    <button type="button" class="btn btn-outline btn-default"
                            onclick="BootstrapTableFunc.toAddForm('#bootTable','/admin/user/to_add.htm','user_1','新增用户',null)">
                        <i class="glyphicon glyphicon-plus" aria-hidden="true">新增</i>
                    </button>
                    <button type="button" class="btn btn-outline btn-default"
                            onclick="BootstrapTableFunc.toEditForm('#bootTable','/admin/user/to_update.htm','user_2','编辑用户')">
                        <i class="glyphicon glyphicon-edit" aria-hidden="true">编辑</i>
                    </button>
                    <div class="btn-group">
                        <button data-toggle="dropdown" class="btn btn-outline btn-default dropdown-toggle" aria-expanded="false">
                            <i class="glyphicon glyphicon-list-alt" aria-hidden="true">状态</i>
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="javascript:void"
                                   onclick="BootstrapTableFunc.updateRows('#bootTable','/admin/user/batch_update_state.do',1)">启用</a>
                            </li>
                            <li><a href="javascript:void"
                                   onclick="BootstrapTableFunc.updateRows('#bootTable','/admin/user/batch_update_state.do',2)">禁用</a>
                            </li>
                            <li><a href="javascript:void"
                                   onclick="BootstrapTableFunc.updateRows('#bootTable','/admin/user/batch_update_state.do',0)">删除</a>
                            <#--<li class="divider"></li>-->
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
<#include "../../commom/include.hplus.js.ftl" />
<#include "../../commom/include.hplus.list.js.ftl" />

<#--<#include "../../commom/include.js.ftl">-->
<script src="/js/system/user/list.js"></script>
</body>
</html>