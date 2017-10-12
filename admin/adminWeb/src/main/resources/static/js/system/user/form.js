/**
 * Created by Administrator on 2017/7/18.
 */
"use strict";
var buttonWatch = null;
$(formSelect).find("button").click(function(){
    var type = $(this).attr("data-type");
    // data("type");
    if (type === "saveAndClose"
        || type === "close"  ){
        buttonWatch = "close";
    }
});

$(document).ready(function() {
    $(formSelect).bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',

        },
//            group : "from-group-body",
        message: 'This value is not valid',
        submitButtons: 'button[name="submitTo"]',
        fields: {
            name: {
                message: '昵称无效',
                validators: {
                    notEmpty: {
                        message: '请填写昵称'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: '昵称在2-10字'
                    },
//                        regexp: {
//                            regexp: /^[a-zA-Z0-9]+$/,
//                            message: 'The username can only consist of alphabetical and number'
//                        },

                }
            },
            loginNum: {
                validators: {
                    notEmpty: {
                        message: '请填写帐号'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: '帐号在2-10字'
                    },
                    different: {
                        field: 'loginPwd',
                        message: '帐号和密码不能相同'
                    }
                }
            },
            loginPwd: {
                validators: {
                    notEmpty: {
                        message: '请填写密码'
                    },
                    different: {
                        field: 'loginNum',
                        message: '密码和帐号不能相同'
                    },
                    stringLength: {
                        min: 6,
                        message: '密码最少6个字母'
                    },
                    identical: {
                        field: 'confirm_loginPwd',
                        message: '密码和确认密码不一致'
                    }
                }
            },
            confirm_loginPwd: {
                validators: {
                    notEmpty: {
                        message: '请填写密码'
                    },
                    different: {
                        field: 'loginNum',
                        message: '密码和帐号不能相同'
                    },
                    stringLength: {
                        min: 6,
                        message: '密码最少6个字母'
                    },
                    identical: {
                        field: 'loginPwd',
                        message: '密码和确认密码不一致'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '请填写邮箱地址'
                    },
                    emailAddress: {
                        message: '邮箱地址无效'
                    }
                }
            },
            phone: {
                validators: {
                    notEmpty: {
                        message: '请输入手机号'
                    },
                    phone : {
                        country : "CN",
                        message : "手机号无效"
                    }
                }
            },
            amount : {
                validators : {
                    notEmpty: {
                        message: '请输入序号'
                    },
                    between: {
                        min: 1,
                        max: 9999,
                        message: '序号范围 1 - 9999'
                    }
                }
            },
            status : {
                validators : {
                    choice: {
                        min: 1,
                        max: 9999,
                        message: '请选择状态'
                    },
                }
            }
        }
    });
}).on('success.form.bv', function(e) {
//        alert("success.form.bv");
    // Prevent form submission
    e.preventDefault();

    // Get the form instance
    var $form = $(e.target);

    // Get the BootstrapValidator instance
    var bv = $form.data('bootstrapValidator');

    // Use Ajax to submit form data
    var url ;
    var data;
    var contentType;
    if (dataEntity.hasOwnProperty("id") && dataEntity.id) {
        url = $form.attr('data-update-action');
        data = dataEntity;
    } else {
        url = $form.attr('data-add-action');
        data = $form.serialize();
    }

    var ajaxOpt = {
        url : url,
        data : data,
        success: function (result) {
            if (result.status) {
                Toast.showSuccess(result.info,"成功");
                setTimeout(function() {
                    // 如果是 *并关闭 按钮 ，当前js有一个事件点击监控事件
                    if (buttonWatch === "close") {
                        parentRefreshBST();
                        closeCurIframe();
                    }
                },200);
            } else{
                Toast.showError(result.info,"失败");
            }
        }
    };
    ajaxWarp(ajaxOpt);

});