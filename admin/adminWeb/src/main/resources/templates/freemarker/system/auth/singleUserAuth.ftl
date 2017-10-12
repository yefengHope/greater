<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
<#include "../../commom/include.hplus.css.ftl" />
<#include "../../commom/include.hplus.form.css.ftl" />
    <title>单用户权限页面</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="page-header">
                <h2>用户权限</h2>
            </div>
            <form id="registrationForm" method="post" class="form-inline">
                <input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="row">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4">昵 称：</label>
                        <div class="col-sm-8">
                            <label>${(userEntity.name)!}</label>
                        </div>
                    </div>

                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">帐 号：</label>
                        <div class="col-sm-8">
                            <label>${(userEntity.loginNum)!}</label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">Email：</label>
                        <div class="col-sm-8">
                            <label>${(userEntity.email)!}</label>
                        </div>
                    </div>

                    <div class="form-group col-sm-6">
                        <label class="col-sm-4 control-label">状态：</label>
                        <div class="col-sm-8">
                            <label>
                            <#if userEntity.status??>
                              <#if userEntity.status == 0>
                                  删除
                              <#elseif userEntity.status == 1>
                                  启用
                              <#elseif userEntity.status == 2>
                                  禁用
                              </#if>
                            </#if>
                            </label>
                        </div>
                    </div>
                </div>

                <div class="col-sm-12">
                    <div class="page-header">
                        <h3>权限列表</h3>
                    </div>
                <#--全部权限列表-->
                <#if roleEntityList??>
                  <#list roleEntityList as role >
                      <div class="checkbox">
                          <label class="checkbox-inline">
                              <input name="status" type="checkbox" value="${role.id}" onclick="authUser('${role.id}','${userEntity.id}')"><i></i>${role.name}</label>
                      </div>
                  </#list>
                </#if>
                </div>
            </form>
        </div>
    </div>
</div>
<#include "../../commom/include.hplus.js.ftl" />
<#include "../../commom/include.hplus.form.js.ftl" />
</body>
<script>
    var roleIds = [<#list userAndRoleEntities as userAndRole>
        ${userAndRole.roleId},
    </#list>];
    $(function () {
        checkedRole(roleIds);
    });
    function checkedRole(roleIds) {
        for (var i in roleIds) {
            var id = roleIds[i];
            $("input[name=status][value=" + id + "]").prop("checked",true);
        }
    }
    function authUser(roleId,userId) {
        ajaxWarp({
            url : basePath + "/admin/system/auth/auth_user.do",
            data : {
                roleId : roleId,
                userId : userId
            }
        })
    }
</script>
</html>