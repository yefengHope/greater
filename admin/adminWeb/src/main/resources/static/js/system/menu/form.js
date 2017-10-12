/**
 * Created by Administrator on 2017/7/18.
 */
"use strict";
var buttonWatch = null;

$(document).ready(function() {
    /*加载时间插件*/
    $(".plug_datetime").datetimepicker({language:"zh-CN"})
    // 选择时间被改变   changeDate
    // 时间选择器被隐藏 hide
        .on('hide', function(ev){
            /*校验字段 ，因为通过js写入的字段不能更新*/
            $(formSelect).bootstrapValidator('updateStatus', ev.target.name, 'NOT_VALIDATED')
                .bootstrapValidator('validateField', ev.target.name);
        });
    // 表单校验
    $(formSelect).bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh',

        },
        message: 'This value is not valid',
        submitButtons: 'button[name="submitTo"]',
        fields: {
            menuType : {
                validators: {
                    notEmpty: {
                        message: '请填写菜单类型'
                    },
                }
            },
            proMenuId : {
                validators: {
                    notEmpty: {
                        message: '请填写父级菜单'
                    },
                    stringLength: {
                        min: 1,
                        max: 40,
                        message: '父级菜单在1-40字'
                    },
                }
            },
            menuLevels : {
                validators: {
                    notEmpty: {
                        message: '请填写菜单层级'
                    },
                    stringLength: {
                        min: 1,
                        max: 40,
                        message: '菜单层级在1-40字'
                    },
                }
            },
            menuName : {
                validators: {
                    notEmpty: {
                        message: '请填写菜单名字'
                    },
                    stringLength: {
                        min: 2,
                        max: 40,
                        message: '菜单名字在2-40字'
                    },
                }
            },
            // menuAddress : {
            //     validators: {
            //         notEmpty: {
            //             message: '请填写菜单地址'
            //         },
            //         stringLength: {
            //             min: 2,
            //             max: 40,
            //             message: '菜单地址在2-40字'
            //         },
            //     }
            // },
            sort : {
                validators: {
                    notEmpty: {
                        message: '请填写序号'
                    },
                    between: {
                        min: 0,
                        max: 100,
                        message: '序号在数字0 - 100之间'
                    },
                }
            },
            status : {
                validators : {
                    notEmpty: {
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
                // 列表页面的处理方法
                // parentRefreshBST();
                // setTimeout(function() {
                //     // 如果是 *并关闭 按钮 ，当前js有一个事件点击监控事件
                //     var isClose = BV.isCloseFrame($form);
                //     if (isClose) {
                //         closeCurIframe();
                //     }
                // },200);

                // 树页面的处理方法
                if (dataEntity.hasOwnProperty("id") && dataEntity.id) {
                    // 编辑节点
                    var node = treeObj.getNodeByParam("id", formVue.$data.id, null);
                    node.menuName = formVue.$data.menuName;
                    treeObj.updateNode(node);
                } else {
                    // 添加节点
                    addNodeWrap(treeSelectName,$(formSelect).data("treeNode"),result.data.id);
                }
            } else{
                Toast.showError(result.info,"失败");
            }
        }
    };
    ajaxWarp(ajaxOpt);
});