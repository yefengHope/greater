var basePath = "";
var bootTableSelector = "#bootTable";
var bootTableSearchSelector = "#bootTableSearch";
var bootTableToolSelector = "#bootTableTool";


/*BootstrapValidator表单校验插件 特殊方法 start*/
var BV = {};
BV.ide = "curClick"; // 标志位
/**
 * 设置当前表单点击的按钮 ，data-curClick = true
 * @param selectForm
 * @param btnName
 */
BV.setBtnClickIde = function (selectForm,btnName) {

    $(selectForm).find("[name=" + btnName + "]").attr("data-" + BV.ide,false);
    var e=window.event;
    $(e.target).attr("data-" + BV.ide,true);
};
/**
 * 获取表单中被点击的按钮的data-type字段
 */
BV.getBtnCurClickDataType= function($selectForm) {
    var $curBtn = $selectForm.find("[data-" + BV.ide+ "=true]");
    if ($curBtn.length !== 1) {
        throw "查找到多个相同点击按钮";
    }
    return $curBtn.data("type");
};

/**
 * 通过当前点击按钮，判断是否关闭
 * 表单中data-curClick=true是当前点击按钮， data-type字段为对应key，判断是否关闭
 * @param $selectForm {Jquery} jquery表单元素
 * @returns {boolean} true ： 关闭 ， false ： 不关闭
 */
BV.isCloseFrame = function ($selectForm) {
    var btnDataType = BV.getBtnCurClickDataType($selectForm);
    if ("saveAndClose" === btnDataType || "close"=== btnDataType ) {
        return true;
    } else {
        return false;
    }
};

/*BootstrapValidator表单校验插件 特殊方法  end */

/* start 提示弹框*/
var Toast = {
    defualtOpt : {
        "closeButton": true,
        "debug": false,
        "progressBar": true,
        "positionClass": "toast-top-center",
        "onclick": null,
        "showDuration": "400",
        "hideDuration": "1000",
        "timeOut": "3000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    },
    i: -1,
    toastCount: 0,
    $toastlast: null,
    getMessage: function () {
        var msg = "Hi, welcome to Inspinia. This is example of Toastr notification box.";
        return msg
    },
    getLast: function () {
        return this.$toastlast;
    },
    clearLast: function () {
        toastr.clear(this.getLast());
    },
    clear: function () {
        toastr.clear()
    }
};
Toast.showSuccess = function (msg,title,opt) {
    opt = $.extend(this.defualtOpt, opt);
    toastr["success"](msg, title);
};
Toast.showInfo = function (msg,title,opt) {
    opt = $.extend(this.defualtOpt, opt);
    toastr["info"](msg, title);
};
Toast.showWarn = function (msg,title,opt) {
    opt = $.extend(this.defualtOpt, opt);
    toastr["warning"](msg, title);
};

Toast.showError = function (msg,title,opt) {
    opt = $.extend(this.defualtOpt, opt);
    toastr["error"](msg, title);
};

/*  end  提示弹框*/


/* start ajax */
function ajaxWarp(option) {
    var loading = null;
    var defualtOtion = {
        contentType : "application/x-www-form-urlencoded",
        //"application/json; charset=utf-8"
        dataType: 'json',
        method: 'post',
        // processData : true, //布尔值，规定通过请求发送的数据是否转换为查询字符串。默认是 true。
        beforeSend: function () {
            //loading层
            loading = layer.load(1, {
                shade: [0.3, '#fff'] //0.1透明度的白色背景
            });
            return true;
        },
        headers : {
            "X-CSRF-TOKEN" : $("#csrfToken").val()
        },
        success: function (data,status,xhr) {
            if (data.status === true) {
                Toast.showSuccess(data.info)
            } else {
                Toast.showError(data.info)
            }
        },
        error: function (data,status,error) {
            var info = data.info;
            if (info) {
                Toast.showError(info);
            }
        },
        complete: function (data,status) {
            if (data.hasOwnProperty("responseText") && data.responseText && $.trim(data.responseText)) {
                data = JSON.parse(data.responseText);
            }
            switch (data.status) {
                case 401 : {
                    Toast.showError("会话已过期，请刷新页面");
                } break;
                case 403 : {
                    if ("Forbidden" == data.error) {
                        Toast.showError("会话Token已过期，请刷新页面");
                    }
                }break;
            }
            layer.close(loading);
        }
    };
    defualtOtion = $.extend(defualtOtion, option);
    if (defualtOtion.data) {
        if (defualtOtion.data.hasOwnProperty("createDate") || defualtOtion.data.hasOwnProperty("createDate")) {
            delete defualtOtion.data.createId;
            delete defualtOtion.data.createName;
            delete defualtOtion.data.createDate;
            delete defualtOtion.data.updateId;
            delete defualtOtion.data.updateName;
            delete defualtOtion.data.updateDate;
        }
    }
    $.ajax(defualtOtion);

}

/*  end  ajax */

/* start layer*/

var Layer = {};
Layer.iframeOpen = function (opt,cancelCallback) {
    var o = {
        type: 2,   //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
        title: 'layer页',    //title: ['文本', 'font-size:18px;']，数组第二项可以写任意css样式
        shadeClose: true,
        shade: 0.8,
        area: ['90%', '90%'],
        content: '' //类型：String/DOM/Array 或URL ,iframe不要滚动条 content: ['http://sentsin.com', 'no']
        ,cancel: function(){
            //右上角关闭回调
            cancelCallback();
            //return false 开启该代码可禁止点击该按钮关闭
        }
    };
    o = $.extend(o,opt);
    //iframe层
    // parent.layer.open(o); //相当于顶级窗口打开
    window.layer.open(o);
};
/**
 * alert 替代，异步
 * @param text
 * @param successCall
 * @param errorCall
 * @param opt
 */
Layer.msg = function (text,successCall,errorCall,opt) {
    if (!text || !$.trim(text)) {
        alert("少年，想什么呢！想弹出什么都不告诉我！");
        return;
    }
    var o = {
        time: 0 //不自动关闭
        ,btn: ['确认', '取消']
        ,yes: function(index) {
            if (typeof successCall === "function") {
                successCall();
            }
            layer.close(index);
        }
        ,btn2: function(index, layero){
            //按钮【按钮二】的回调
            if (typeof errorCall === "function"){
                errorCall();
            }
        }
    };
    o = $.extend(o,opt);
    layer.msg(text, o);
};
//----- layer date start -----
var LayerDate = {};
LayerDate.opt = {
    elem: '.layer-date', //需显示日期的元素选择器
    event: 'click', //触发事件
    format: 'YYYY-MM-DD hh:mm:ss', //日期格式
    istime: true, //是否开启时间选择
    // format: 'YYYY-MM-DD', //日期格式
    // istime: false, //是否开启时间选择
    isclear: true, //是否显示清空
    istoday: true, //是否显示今天
    issure: true, //是否显示确认
    festival: true ,//是否显示节日
    min: '1950-01-01 00:00:00', //最小日期
    max: '2099-12-31 23:59:59', //最大日期
    // start: '2014-6-15 23:00:00',  //开始日期
    fixed: false, //是否固定在可视区域
    zIndex: 99999999, //css z-index
    choose: function(dates){ //选择好日期的回调
    }
};
LayerDate.load = function(opt){
    var o = $.extend(LayerDate.opt,opt);
    laydate(o);
    laydate.skin("molv");  //加载皮肤，参数lib为皮肤名
};

//----- layer date  end  -----

/*  end  layer*/

/* start 列表方法*/
/**
 * 刷新bootstrap table 方法
 * @param selector          bootstrap table 选择器
 * @param selectorSearch    bootstrap table的搜索表单选择器
 * @constructor
 */
function refreshBST(selector,selectorSearch) {
    var stor = null;
    if (selectorSearch) {
        stor = selectorSearch
    } else {
        stor = bootTableSearchSelector;
    }
    $(selector).bootstrapTable("refresh",{
        query: $(stor).getBSTSearchParams(),
    });
}

/** 刷新父iFrame中的list列表，后台
 * ps : 必须放在关闭iFrame之前
 * @param btSelect
 * @param btFormSelect
 */
function parentRefreshBST(btSelect,btFormSelect){
    var stor = null;
    if (btFormSelect) {
        stor = btFormSelect
    } else {
        stor = bootTableSearchSelector;
    }

    if (!btSelect) {
        btSelect = bootTableSelector;
    }
    parent.$(btSelect).bootstrapTable("refresh",{
        query: parent.$(stor).getBSTSearchParams(),
    });
}
var BootstrapTableFunc = {};
/**
 * 跳转到添加表单
 * @param selector   bootstrap table 的选择器
 * @param url       表单访问地址
 * @param tapsIndex  选项卡索引
 * @param tapsName   选项卡名字
 * @param data      携带参数
 * @param selectorSearch      bootstrap table 搜索表单选择器
 **/
BootstrapTableFunc.toAddForm = function (selector,url, tapsIndex, tapsName, data,selectorSearch) {
    if (!url) {
        throw "url is empty";
    }
    if (!data) {
        data = {};
    }
    Layer.iframeOpen({title:tapsName,content : url},function () {
        refreshBST(selector,selectorSearch);
    })
};
/**
 * 跳转编辑表单,自动获取表单id
 * @param selector   bootstrap table 的选择器
 * @param url        表单访问地址
 * @param tapsIndex  选项卡索引
 * @param tapsName   选项卡名字
 * @param data       传入参数
 * @param selectorSearch      bootstrap table 搜索表单选择器
 **/
BootstrapTableFunc.toEditForm = function (selector, url, tapsIndex, tapsName, data, selectorSearch) {
    if (!selector || !url) {
        throw "selector or url is empty";
    }
    var arr = $(selector).bootstrapTable("getSelections");
    if ( 1 === arr.length) {

        if (!data) {
            data = {};
        }

        var o = arr[0];
        if (o.hasOwnProperty("id")) {
            data.id = o.id;
            url += "?id=" + o.id
        } else {
            console.error("key -> id not exist");
        }
        Layer.iframeOpen({title:tapsName,content : url},function () {
            refreshBST(selector,selectorSearch);
        });
    } else {
        Toast.showInfo("请选中一条数据");
    }


};
/**
 * 批量更新行数据
 * @param selector  bootstrap table 的选择器
 * @param url       表单访问地址
 * @param status    状态值
 * @param selectorSearch      bootstrap table 搜索表单选择器
 **/
BootstrapTableFunc.updateRows = function (selector, url,status, selectorSearch) {
    var data;
    if (selector && url) {
        if (!data) {
            data = {};
        }

        var arr = $(selector).bootstrapTable("getSelections");
        var tArr = [];
        for (var i = 0, l = arr.length; i < l; i++) {
            var o = arr[i];
            if (o.hasOwnProperty("id")) {
                tArr.push(o.id);
            } else {
                console.error("key -> id not exist");
            }
        }
        if (tArr.length <= 0){
            Toast.showWarn("请选择勾选一条数据！");
            return;
        }
        data.ids = tArr.join(",");
        data.status = status;
        ajaxWarp({
            url: url,
            data: data,
            success: function (result) {
                if (result.status) {
                    Toast.showSuccess(result.info);
                } else {
                    alert(result.info);
                }
                refreshBST(selector,selectorSearch);
            }
        });
    } else {
        throw "selector or url is empty";
    }
};

// 获取搜索表单参数
$.fn.getBSTSearchParams = function () {
    var rObj = {};
    var params = $(this).serializeArray();
    for (var i=0,s=params.length;i<s;i++){
        var o = params[i];
        if ($.trim(o.value)){
            rObj[o.name] = o.value;
        }
    }
    return rObj;
};

// 清空搜索表单
function clearSearchForm(select) {
    var $form = $(select);
    $form.find("input[type=text],input[type=number]").val("");
    $form.find("select").val("");
}

//----- 表格脚本方法开始 -----
function BSTStatusformatter (value,row,index){
    switch (value) {
        case 0 : {return "删除"} break;
        case 1 : {return "启用"} break;
        case 2 : {return "禁用"} break;
        default : {
            return "未知-状态";
        }
    }
}


/*  end  列表方法*/

// ------------------ 分割线 -  ------------------


// 关闭当前iframe
function closeCurIframe() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //执行关闭自身操作
}


