/**
 * Created by yic on 2017/2/6.
 */
$(function(){
// 左边导航栏高度 start
    function contSide(){
        var menuBoxH = $(".menu-box").height();
        var screenH = $(window).height() -110;
        if(menuBoxH > screenH){
            $(".cont-side").addClass("cont-side-many");
        }else{
            $(".cont-side").removeClass("cont-side-many");
        }
    }
    contSide();
    $(window).resize(function(){
        contSide();
    });
// 左边导航栏高度 end
// input 聚焦边框变色 start
    $(".blue-border-focus").focus(function(){
        if($(this).attr("readonly") != "readonly"){
            $(this).addClass("active");
        }
    });
    $(".blue-border-focus").blur(function(){
        if($(this).attr("readonly") != "readonly"){
            $(this).removeClass("active");
        }
    });
    $(".focus-change").focus(function(){
        if($(this).attr("readonly") != "readonly"){
            $(this).parent().addClass("active");
        }
    });
    $(".focus-change").blur(function(){
        if($(this).attr("readonly") != "readonly"){
            $(this).parent().removeClass("active");
        }
    });
    $(".focus-change-self").focus(function(){
        if($(this).attr("readonly") != "readonly"){
            $(this).addClass("active");
        }
    });
    $(".focus-change-self").blur(function(){
        if($(this).attr("readonly") != "readonly"){
            $(this).removeClass("active");
        }
    });
// input 聚焦边框变色 end
//批量编辑价格
    $(".edit-icon-box>.tit").click(function(){
        if($(this).parent(".edit-icon-box").find(".batch-update-pop").css("display") == "none"){
            $(this).parent(".edit-icon-box").find(".batch-update-pop").addClass("open");
        }else{
            $(this).parent(".edit-icon-box").find(".batch-update-pop").removeClass("open");
        }
    });
    $(".batch-update-pop>.set-up").click(function(){
        $(this).parents(".batch-update-pop").removeClass("open");
        var batchNegotiablePrice = $(this).parents(".batch-update-pop").find(".focus-change-self").val();
        if(batchNegotiablePrice != ""){
            $(this).parents(".table").find(".price-set-up").each(function(){
                $(this).find(".focus-change-self").attr("value",batchNegotiablePrice);
            })
        }
    });
//面议-勾选
    $(".checkbox-piece").click(function(e){
        //if ($(e.target).is('input')) {
        //    return;
        //}
        if($(this).find("input[type='checkbox']").is(":checked")){
            $(this).parents(".price-set-up").find(".focus-change-self").attr("readonly","readonly");
        }else{
            $(this).parents(".price-set-up").find(".focus-change-self").removeAttr("readonly");
        }
    });
    $(".price-set-up").each(function(){
        if($(this).find("input[type='checkbox']").is(":checked")){
            $(this).find(".focus-change-self").attr("readonly","readonly");
        }else{
            $(this).find(".focus-change-self").removeAttr("readonly");
        }
        $(this).find(".focus-change-self").blur(function(){
            var negotiablePrice = $(this).val();
            $(this).attr("value",negotiablePrice);
        })
    });


});
//layer 弹出层 start
//样式  标题栏为深灰色
function deeppurepop(tit,id){
    var wi=$(id).width()+'px';
    var index=layer.open({
        title:[tit,"background:#979dad;color:#f2f6f9;font-size:16px;line-height:50px;height:50px; padding:0 50px 0 20px;"],
        type:1,
        area:'wi',
        content: $(id),
        skin: 'deep-pure-pop',
        shade: [0.5, '#fff'],
        success: function(){
            //scrollTableW();
        }
    });
    $(".myclose").on("click",function(){
        layer.close(index);
    });
    $('.chosen-container').css("width","150px");
}
//样式  标题栏为灰色
function purepop(tit,id){
    var wi=$(id).width()+'px';
    var index=layer.open({
        title:[tit,"background:#e6e8ec;color:#004b92;font-size:16px;line-height:48px;height:48px; padding:0 50px 0 20px;"],
        type:1,
        area:'wi',
        content: $(id),
        skin: 'business-center-layer',
        shade: [0.5, '#fff'],
        success: function(){
            //scrollTableW();
        }
    });
    $(".myclose").on("click",function(){
        layer.close(index);
    });
    $('.chosen-container').css("width","150px");
}
//样式  标题栏为白色
function whitepop(tit,id){
    var wi=$(id).width()+'px';
    var index=layer.open({
        title:[tit,"background:#fff;color:#999;font-weight: 700;font-size:18px;line-height:58px;height:58px; padding:0 50px 0 20px; border-bottom: 1px solid #eee;"],
        type:1,
        area:'wi',
        content: $(id),
        skin: 'white-pop-up',
        shade: [0.5, '#fff'],
        success: function(){
            //scrollTableW();
        }
    });
    $(".myclose").on("click",function(){
        layer.close(index);
    });
    $('.chosen-container').css("width","150px");
}
//layer 弹出层 end
//滚动表格宽度设置
//function scrollTableW(){
//        $(".scroll-table").each(function(){
//            $(this).find(".scroll-table-thead>thead>tr>th").each(function(i){
//                var correspondingH = $(this).parents(".scroll-table").find(".scroll-table-tbody>tbody>tr>td").eq(i);
//                var currentH = $(this).width() + parseInt($(this).css("paddingLeft")) + parseInt($(this).css("paddingRight"));
//                //console.log(parseInt($(this).css("paddingLeft")))
//                //console.log(parseInt($(this).css("paddingRight")))
//                //console.log(currentH)
//                correspondingH.width(currentH);
//            })
//        });
//}
//tab 切换
;(function(){
    var Tabs = function(ele,opt){
        this.$element=ele,
            defaults = {
                outBox:'',//最外层div类名
                tabTitle:'',//操作tab层
                content:'',//切换tab外出
                checkStyle:'active',//tab选中样式类名
                eve:'click'//触发事件,只有click与hover这两个参数
            }
            this.options=$.extend({}, defaults,opt)
    };
    Tabs.prototype = {
        start:function(){
            var outBox=$(this.$element).selector,
                content=this.options.content,
                tabTitle=this.options.tabTitle,
                eve=this.options.eve,
                checkStyle=this.options.checkStyle;
            $(this.$element).each(function(){
                var i=0;
                var ishas=$(this).children(tabTitle).children().hasClass(checkStyle);
                $(this).children(tabTitle).children().each(function(){
                    if(ishas) {
                        if($(this).hasClass(checkStyle)==true) {
                            $(this).parent().parent(outBox).find(content).children().hide();
                            $(this).parent().parent(outBox).find(content).children().eq(i).show();
                        }
                    }else{
                        $(this).parent().children().eq(0).addClass(checkStyle);
                        $(this).parent().parent(outBox).find(content).children().hide();
                        $(this).parent().parent(outBox).find(content).children().eq(0).show();
                    }
                    $(this).attr('data-num',i);
                    i++;
                });
            });
            if(eve=="hover") {
                $(this.$element).children(tabTitle).children().hover(function(){
                    var opt_num=$(this).attr('data-num');
                    $(this).parent().parent(outBox).children(content).children().hide();
                    $(this).parent().parent(outBox).children(content).children().eq(opt_num).show();
                    $(this).parent().children().removeClass(checkStyle);
                    $(this).addClass(checkStyle);
                });
            }
            else if(eve=="click") {
                $(this.$element).children(tabTitle).children().click(function(){
                    var opt_num=$(this).attr('data-num');
                    $(this).parent().parent(outBox).children(content).children().hide();
                    $(this).parent().parent(outBox).children(content).children().eq(opt_num).show();
                    $(this).parent().children().removeClass(checkStyle);
                    $(this).addClass(checkStyle);
                });
            }
        }
    };
    $.fn.myTab = function(options) {
        var tabs = new Tabs(this,options);
        return tabs.start();
    };
})();
//全选
function checkAll(e,allEle,eveEle){
    if ($(e.target).is('input')) {
        return;
    }
    var checkTr = $(eveEle).parents("tr");
    if($(allEle).find("input[type='checkbox']").is(":checked")){
        $(eveEle).find("input[type='checkbox']").attr("checked", false);
        $(eveEle).removeClass("checked");
        checkTr.removeClass("checked-tr");
    }else{
        $(eveEle).find("input[type='checkbox']").attr("checked", true);
        $(eveEle).addClass("checked");
        checkTr.addClass("checked-tr");
    }
}
//多选框样式
$(".checkbox-piece").click(function(){
    var checkTr = $(this).parents("tr");
    if($(this).find("input[type='checkbox']").is(":checked")){
        $(this).addClass("checked");
        checkTr.addClass("checked-tr");
    }else{
        $(this).removeClass("checked");
        checkTr.removeClass("checked-tr");
    }
});
//滑动按钮
$(".custom-price>.slide-bth").each(function(){
    if($(this).find("span").text() == "Y"){
        $(this).addClass("slide-define");
    }else{
        $(this).removeClass("slide-define");
    }
});

$(document).on("click",".custom-price>.slide-bth",function () {
    if($(this).find("span").text() == "N"){
        $(this).find("span").stop().animate({
            left: 17
        },400);
        $(this).addClass("slide-yes");
        $(this).find("span").text("Y");
    }else{
        $(this).find("span").stop().animate({
            left: 1
        },400);
        $(this).removeClass("slide-yes");
        $(this).removeClass("slide-define");
        $(this).find("span").text("N");
    }
});
//动态添加面议多选框id
$(".dynamic-id").each(function(e){
    if ($(e.target).is('input')) {
        return;
    }
    var dynamicIdEle = $(this).parents("tr").index();
    $(this).find("input").attr("id","negotiable" + dynamicIdEle);
    $(this).attr("for","negotiable" + dynamicIdEle)
});
