<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>后台 - 登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="/html_model/hplus/css/login.min.css" rel="stylesheet">
<#include "../commom/include.hplus.css.ftl" />
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location
        }
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>[ H+ ]</h1>
                </div>
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>H+ 后台主题UI框架</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                </ul>
                <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
            </div>
        </div>
        <div class="col-sm-5">
            <form method="post" action="/login.htm">
                <h4 class="no-margins">登录：</h4>
                <p class="m-t-md">登录到后台管理</p>
                <div>
                <#if (param.error)!false >
                    <span>帐号或密码错误</span>
                <#elseif (param.logout)!false >
                    <span>您已经退出登录</span>
                </#if>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input name="userName" type="text" class="form-control uname" placeholder="用户名"/>
                <input name="userPwd" type="password" class="form-control pword m-b" placeholder="密码"/>
                <a href="">忘记密码了？</a>
                <#--<button class="btn btn-success btn-block" type="submit">登录</button>-->
            <button class="btn btn-success btn-block" type="button" onclick="login()">登录</button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
        <#include "../commom/include.footer.ftl"/>
        </div>
    </div>
</div>
</body>
<script src="/html_model/hplus/js/jquery.min.js-v=2.1.4.js"></script>
<script src="/html_model/hplus/js/bootstrap.min.js-v=3.3.5.js"></script>
<script src="/html_model/hplus/js/content.min.js-v=1.0.0.js"></script>
<script src="/html_model/hplus/js/contabs.min.js" type="text/javascript" ></script>
<script src="/html_model/hplus/js/plugins/toastr/toastr.min.js"></script>
<script src="/plugs/layer_v3_0_1/layer.js"></script>
<script src="/js/common/admin.common.js"></script>
<#--<script src="/html_model/hplus/js/hplus.min.js-v=4.0.0.js"></script>-->
<script src="/html_model/hplus/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/html_model/hplus/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="/html_model/hplus/js/plugins/pace/pace.min.js"></script>

<#--<#include "../commom/include.hplus.js.ftl" />-->
<script>
    function login() {
        ajaxWarp({
            url: basePath + "/login.htm",
            data: $("form").serialize(),
            success: function (data,status,xhr) {
                if (data.status === true) {
                    location.href = encodeURI(basePath + "/admin/index.htm");
//                    Toast.showSuccess(data.info);
//                    setTimeout(function () {},500);
                } else {
                    Toast.showError(data.info)
                }
            },
        })
    }
</script>
</html>