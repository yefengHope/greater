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
                  data-add-action="/admin/role/add.do" data-update-action="/admin/role/update.do">
                <input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">角色名称</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="name" v-model="name" v-bind:disabled="id"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">角色键名</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="nameKey" v-model="nameKey" v-bind:disabled="id"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-3 control-label">业务系统编号</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="systemId" v-model="systemId"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">角色有效期</label>
                    <div class="col-sm-5 input-append">
                        <input id="validity" type="text" size="16" readonly="readonly"
                               class="form-control plug_datetime" name="validity" v-model="validity"/>
                        <span class="add-on"><i class="icon-remove"></i></span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">菜单访问权限</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="arights" v-model="arights"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">菜单操作权限</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="hrights" v-model="hrights"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">序号</label>
                    <div class="col-sm-5">
                        <input type="number" class="form-control" name="sort" v-model="sort"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">状态</label>
                    <div class="col-sm-5">
                    <#--<select name="status" placeholder="状态" class="form-control" v-model="status">-->
                    <#--<option value="1">启用</option>-->
                    <#--<option value="2">禁用</option>-->
                    <#--</select>-->
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
<script src="/js/system/role/form.js"></script>
</body>
</html>