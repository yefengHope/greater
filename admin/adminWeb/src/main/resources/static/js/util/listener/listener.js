/**
 * js事件监听器
 * 主要方式:
 *      通过js事件冒泡,在document层监听事件
 *      事件模型:捕获和冒泡
 *
 *      （事件在这里就像一个观光客，由外至内游览，逐渐接近被触发的主要元素，然后又反向离开）
 *
 *  1.单击事件首先进入捕获阶段开始（逐渐接近元素2的方向）。查看元素2的祖先元素中是否有在捕获阶段有onclick处理函数的
 *      发现元素1有一个，于是doSomething2被执行
 *  2.事件检查到目标自己（元素2），捕获阶段没有发现更多的处理函数了。事件开始进入冒泡阶段，想当然执行doSomething()，这个绑定于元素2冒泡阶段的函数。
 *  3.事件向远离元素2的方向，查看是否有任何祖先元素在冒泡阶段绑定了一个处理函数。没有这样的情况，所以什么也没有发生
                  | |  / \
 -----------------| |--| |-----------------
 | element1       | |  | |                |
 |   -------------| |--| |-----------     |
 |   |element2    \ /  | |          |     |
 |   --------------------------------     |
 |        W3C event model                 |
 ------------------------------------------
 *
 * Created by 韩峰 on 2016/11/3.
 */

var listener = {

};

/**
 * 用户在页面的停留时间
 * 在页面做了什么
 * 页面长时间没有任何改变
 * 那么websocket是否可以离线
 */

/**
 * 点击监听事件
 * event = r.Event {
 *      originalEvent: MouseEvent,
 *      type: "click",
 *      target: input,
 *      currentTarget: document,
 *      relatedTarget: null…
 * }
 */
$(document).on("click",function (event) {
    var curObj = $(event.target);   //点击发生的地方
    curObj.each(function (index) {
       /* console.log("我来自listener监听函数:");
        console.log(index,this.name,this.value);*/
    });
});

/**
 * 键盘事件
 */
