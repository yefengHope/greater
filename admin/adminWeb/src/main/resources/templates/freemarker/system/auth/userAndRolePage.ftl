<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <title>用户角色关联</title>
<#include "../../commom/include.hplus.css.ftl" />
<#include "../../commom/include.hplus.list.css.ftl" />
<#include "../../commom/include.zTree.css.ftl" />
</head>
<body class="gray-bg">
<input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h3>角色列表</h3>
                    <ul id="treeDemo" class="ztree">

                    </ul>
                </div>
            </div>
        </div>
    <#--start 用户数据列表-->
        <div class="col-sm-9">
            <#--start 工具栏-->
                <div class="btn-group hidden-xs" id="bootTableTool" role="group">
                    <button type="button" class="btn btn-outline btn-default" title="关联左侧角色列表当前选中角色"
                            onclick="batchAuthUser('#treeDemo','#bootTable')">
                        <i class="glyphicon glyphicon-plus" aria-hidden="true">批量关联角色</i>
                    </button>
                    <button type="button" class="btn btn-outline btn-default"
                            onclick="BootstrapTableFunc.toEditForm('#bootTable','/admin/system/auth/user_role/single_user.htm','role_2','编辑')">
                        <i class="glyphicon glyphicon-edit" aria-hidden="true">编辑单用户授权</i>
                    </button>
                </div>
            <#-- end  工具栏-->
        <#--start 搜索栏-->
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
        <#--end 搜索栏-->
        <#--start 用户列表-->
            <div>
                <table id="bootTable"></table>
            </div>
        <#--end 用户列表-->
        </div>
    <#-- end  用户数据列表-->
    </div>
</div>
<#include "../../commom/include.hplus.js.ftl" />
<#include "../../commom/include.hplus.list.js.ftl" />
<#include "../../commom/include.zTree.js.ftl" />
<script>
    var roleEntityList = ${roleEntityList!'[]'};
</script>
<script src="/js/system/auth/userAndRolePage.js"></script>
</body>
</html>