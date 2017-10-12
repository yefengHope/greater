/**
 * 函数基础
 * Created by rain on 2016/7/23.
 */

/**
 * 函数的声明和调用
 *      函数也是对象
 */
//函数声明
function sum(a, b) {
    return Number(a) + Number(b);
}
//函数调用
sum(4, 5);

/**
 * 匿名函数
 *      实质为匿名函数表达式,而非语句
 *      function后面可省略函数名
 *      可以有返回值,返回值是一个Function对象的引用
 *      注:js解析时,必须表达式在前,调用才后,和函数不一样
 */

//将Function对象的一个引用赋值给变量sum2
var sum2 = function(a, b) {
    return Number(a) + Number(b);
}

//调用sum2
sum2(5, 6);

var sum3 = sum;
sum3(7, 8);


//-- 自执行函数 --
(function jiajiajai() {
    console.log("jiajiajai")
})();
//jiajiajai

var abc = (function() {
    console.log("我在自动执行");
    return 5;
})();
abc; //5

var abc = (function() {
    console.log("我在自动执行");
})();
abc; //undefined