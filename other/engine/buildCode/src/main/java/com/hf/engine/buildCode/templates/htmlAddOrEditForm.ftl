<#assign configRequestMapping>
    <#if fullConfig["controller.requestMapping"]??>${fullConfig["controller.requestMapping"] ?trim}</#if>
</#assign>
<#assign configPrefix>
    <#if fullConfig["controller.impl.ftl.prefix"]??>${fullConfig["controller.impl.ftl.prefix"] ?trim}</#if>
</#assign>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <title>角色表单</title>
    ${r'<#include "../../commom/include.hplus.css.ftl" />'}
    ${r'<#include "../../commom/include.hplus.form.css.ftl" />'}
</head>
<body>

<div id="formWrapDiv" class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="page-header">
                <h2>{{id ? "修改":"新增"}}</h2>
            </div>

            <form id="registrationForm" method="post" class="form-horizontal"
                  data-add-action="${configRequestMapping?trim}/add.do" data-update-action="${configRequestMapping?trim}/update.do">
                <input type="hidden" id="csrfToken" name="${r'${_csrf.parameterName}'}" value="${r'${_csrf.token}'}"/>
                <#--<div class="form-group">-->
                    <#--<label class="col-sm-3 control-label">角色名称</label>-->
                    <#--<div class="col-sm-5">-->
                        <#--<input type="text" class="form-control" name="name" v-model="name" v-bind:disabled="id"/>-->
                    <#--</div>-->
                <#--</div>-->
            <#--如果data.fields 存在,则循环写入变量-->
            <#if data.fieldModels??>
                <#list data.fieldModels as fildModels>
                    <#if fildModels.lowerCamelCaseName?matches("^((create)|(update))[A-Z][A-Za-z0-9]*")
                    || fildModels.lowerCamelCaseName == "id">
                    <#else >
                    <div class="form-group">
                        <label class="col-sm-3 control-label">${fildModels.comment}</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="${fildModels.lowerCamelCaseName}"
                                   v-model="${fildModels.lowerCamelCaseName}"/>
                        </div>
                    </div>
                    </#if>
                </#list>
            </#if>

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

${r'<#include "../../commom/include.hplus.js.ftl" />'}
${r'<#include "../../commom/include.hplus.form.js.ftl" />'}
<script>
    var formSelect = "#registrationForm";
    var dataEntity = ${r"${dataEntity!'{}'}"};
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
<script src="/js/${configPrefix?trim}/form.js"></script>
</body>
</html>