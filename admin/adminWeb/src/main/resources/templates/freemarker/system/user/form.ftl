<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
<#include "../../commom/include.hplus.css.ftl" />
<#include "../../commom/include.hplus.form.css.ftl" />
    <title>用户表单</title>
</head>
<body>

<div id="formWrapDiv" class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="page-header">
                <h2>{{id ? "修改":"注册"}}</h2>
            </div>

            <form id="registrationForm" method="post" class="form-horizontal"
                  data-add-action="/admin/user/add.do" data-update-action="/admin/user/update.do">
                <input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">昵 称</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="name" v-model="name"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">帐 号</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="loginNum" v-model="loginNum" v-bind:disabled="id"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-3 control-label">密 码</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" name="loginPwd" v-model="loginPwd" v-bind:disabled="id"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">确认密码</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" name="confirm_loginPwd" v-bind:disabled="id"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">Email</label>
                    <div class="col-sm-5">
                        <input type="email" class="form-control" name="email" v-model="email"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">手机号</label>
                    <div class="col-sm-5">
                        <input type="tel" class="form-control" name="phone" v-model="phone"/>
                    </div>
                </div>

            <#--<div class="form-group">-->
            <#--<label class="col-sm-3 control-label">序号</label>-->
            <#--<div class="col-sm-5">-->
            <#--<input type="number" class="form-control" name="sort" v-model="sort"/>-->
            <#--</div>-->
            <#--</div>-->
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
                <#--<div class="form-group">
                    <label class="col-sm-3 control-label">测试</label>
                    <div class="col-sm-5">
                        <div class="input-group">
                            <div class="input-group-addon">$</div>
                            <input type="text" class="form-control" name="amount" placeholder="Amount">
                            <div class="input-group-addon">.00</div>
                        </div>
                    </div>
                </div>-->
                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        <button name="submitTo" type="submit" class="btn btn-default" v-if="!id">注 册</button>
                        <button name="submitTo" type="submit" class="btn btn-default" data-type="saveAndClose" v-if="!id">注册并关闭</button>
                        <button name="submitTo" type="submit" class="btn btn-default" v-if="id">修 改</button>
                        <button name="submitTo" type="submit" class="btn btn-default" data-type="saveAndClose" v-if="id">修改并关闭</button>
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
<script src="/js/system/user/form.js"></script>
</body>
</html>