<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <title>角色表单</title>
<#include "../../commom/include.hplus.css.ftl" />
<#include "../../commom/include.hplus.form.css.ftl" />
</head>
<body>

<div id="formWrapDiv" class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="page-header">
                <h2>{{id ? "修改":"新增"}}</h2>
            </div>

            <form id="registrationForm" method="post" class="form-horizontal"
                  data-add-action="/admin/system/menu/add.do" data-update-action="/admin/system/menu/update.do">
                <input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">菜单类型</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="menuType"
                               v-model="menuType"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">父级菜单</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="proMenuId"
                               v-model="proMenuId" disabled="disabled"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">菜单层级</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="menuLevels"
                               v-model="menuLevels" disabled="disabled"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">菜单名字</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="menuName"
                               v-model="menuName"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">菜单地址</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="menuAddress"
                               v-model="menuAddress"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">序号</label>
                    <div class="col-sm-5">
                        <input type="number" class="form-control" name="sort"
                               v-model="sort"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">状态</label>
                    <div class="col-sm-5">
                        <div class="radio">
                            <label class="radio-inline">
                                <input name="status" v-model="status" type="radio" value="1"><i></i>启用</label>
                            <label class="radio-inline">
                                <input name="status" v-model="status" type="radio" value="2"><i></i>禁用</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        <!-- Do NOT use name="submit" or id="submit" for the Submit button -->
                        <button name="submitTo" type="submit" class="btn btn-default"
                                onclick="BV.setBtnClickIde('#registrationForm','submitTo')" v-if="!id">新增</button>
                        <button name="submitTo" type="submit" class="btn btn-default"
                                onclick="BV.setBtnClickIde('#registrationForm','submitTo')"
                                data-type="saveAndClose" v-if="!id">新增并关闭</button>
                        <button name="submitTo" type="submit" class="btn btn-default"
                                onclick="BV.setBtnClickIde('#registrationForm','submitTo')" v-if="id">修 改</button>
                        <button name="submitTo" type="submit" class="btn btn-default"
                                onclick="BV.setBtnClickIde('#registrationForm','submitTo')"
                                data-type="saveAndClose" v-if="id">修改并关闭</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<#include "../../commom/include.hplus.js.ftl" />
<#include "../../commom/include.hplus.form.js.ftl" />
<script>
    var formSelect = "#registrationForm";
    var dataEntity = ${dataEntity!'{}'};
    $(function () {
        if (dataEntity.hasOwnProperty("createDate")){
            delete dataEntity.createDate
        }
        if (dataEntity.hasOwnProperty("updateDate")){
            delete dataEntity.updateDate
        }
    });
    var formVue = new Vue({
        el: "#formWrapDiv",
        data: dataEntity,
    });
</script>
<script src="/js/system/menu/form.js"></script>
</body>
</html>