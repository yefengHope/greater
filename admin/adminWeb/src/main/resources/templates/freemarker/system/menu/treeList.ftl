<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <title>树形菜单</title>
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
                    <div class="alert alert-info" role="alert">鼠标移动在菜单上，显示操作按钮</div>
                    <h3>菜单列表</h3>
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
        <div class="col-sm-9">
            <form id="rightForm" method="post" class="form-horizontal" style="display: none"
                  data-add-action="/admin/system/menu/add.do" data-update-action="/admin/system/menu/update.do">
                <div class="ibox">
                    <div class="ibox-content">

                        <div class="page-header">
                            <h2>{{id ? "修改":"新增"}}</h2>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">菜单类型</label>
                            <div class="col-sm-5">
                                <div class="radio">
                                    <label class="radio-inline">
                                        <#--value 和 数据 menu id对应-->
                                        <input name="menuType" disabled="disabled" v-model="menuType" type="radio" value="1"><i></i>用户端菜单（前台）</label>
                                    <label class="radio-inline">
                                        <input name="menuType" disabled="disabled" v-model="menuType" type="radio" value="2"><i></i>管理端菜单（后台）</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">父级菜单</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" name="proMenuId"
                                       v-model="proMenuId" readonly="readonly"/>
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
                                       v-model="sort" value="1"/>
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
                                        onclick="BV.setBtnClickIde('#registrationForm','submitTo')" v-if="id">修 改</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
<#include "../../commom/include.hplus.js.ftl" />
<#include "../../commom/include.hplus.form.js.ftl" />
<#include "../../commom/include.zTree.js.ftl" />
    <script>
        var formSelect = "#rightForm";
        var menuEntityList = ${menuEntityList!'[]'};
        var dataEntity = ${dataEntity!'{}'};
        var formVue = new Vue({
            el: formSelect,
            data: dataEntity,
        });
    </script>
    <script src="/js/system/menu/treeList.js"></script>
    <script src="/js/system/menu/form.js"></script>
</body>
</html>