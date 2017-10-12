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

        <div class="col-sm-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h3>菜单列表</h3>
                    <ul id="treeMenu" class="ztree">

                    </ul>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="ibox">
                <div class="ibox-content">
                    <h3>操作列表</h3>
                    <div>
                        <div class="btn-group hidden-xs" id="bootTableTool" role="group">
                            <button type="button" class="btn btn-outline btn-default" title="关联左侧角色列表当前选中菜单"
                                    onclick="batchAuthMenu('#treeDemo','#treeMenu')">
                                <i class="glyphicon glyphicon-plus" aria-hidden="true">批量关联菜单</i>
                            </button>
                        <#--<button type="button" class="btn btn-outline btn-default"-->
                        <#--onclick="BootstrapTableFunc.toEditForm('#bootTable','/admin/system/auth/user_role/single_user.htm','role_2','编辑')">-->
                        <#--<i class="glyphicon glyphicon-edit" aria-hidden="true">编辑单用户授权</i>-->
                        <#--</button>-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../../commom/include.hplus.js.ftl" />
<#include "../../commom/include.hplus.list.js.ftl" />
<#include "../../commom/include.zTree.js.ftl" />
<script>
    var roleEntityList = ${roleEntityList!'[]'};
    var menuEntityList = ${menuEntityList!'[]'};
</script>
<script src="/js/system/auth/menuAndRolePage.js"></script>
</body>
</html>