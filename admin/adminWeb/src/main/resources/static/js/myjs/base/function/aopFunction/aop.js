/**
 * AOP（面向切面编程）的主要作用:
 * 
 *      是把一些跟核心业务逻辑模块无关的功能抽离出来，
 * 这些跟业务逻辑无关的功能通常包括日志统计、安全控制、异常处理等。把这些功能抽离出来之后，
 * 再通过“动态织入”的方式掺入业务逻辑模块中。
 *      这样做的好处首先是可以保持业务逻辑模块的纯净和高内聚性，其次是可以很方便地复用日志统计等功能模块。
 * 
 * 在 Java 语言中，可以通过反射和动态代理机制来实现 AOP 技术。
 * 而在 JavaScript 这种动态语言中，AOP的实现更加简单，这是 JavaScript与生俱来的能力。
 * 通常，在 JavaScript中实现 AOP，都是指把一个函数“动态织入”到另外一个函数之中，
 * 具体的实现技术有很多，本节我们通过扩展 Function.prototype 来做到这一点。代码如下：
 * 
 * >>>>>>>对apply有疑问,请看/this/call_apply.js
 */

Function.prototype.before = function(beforefn) {
    var __self = this; // 保存原函数的引用
    return function() { // 返回包含了原函数和新函数的"代理"函数
        beforefn.apply(this, arguments); // 执行新函数，修正 this
        return __self.apply(this, arguments); // 执行原函数
    }
};
Function.prototype.after = function(afterfn) {
    var __self = this;
    return function() {
        var ret = __self.apply(this, arguments);
        afterfn.apply(this, arguments);
        return ret;
    }
};
var func = function() {
    // debugger;
    console.log(2);
};

/**
函数解释:

(function() { // 返回包含了原函数和新函数的"代理"函数
    beforefn.apply(this, arguments); // 执行新函数，修正 this
    return __self.apply(this, arguments); // 执行原函数
}).after(function() {
    console.log(3);
});
 */
func = func.before(function() {
    console.log(1);
}).after(function() {
    console.log(3);
});
func();
/**
 * 输出:
 * 1
 * 2
 * 3
 */

//当执行到after()时 ,this指针为undefined


// 所以修改以上方法, 使after() 能接收到引用
Function.prototype.before = function(beforefn) {
    debugger;
    var __self = this; // 保存原函数的引用
    return function() { // 返回包含了原函数和新函数的"代理"函数
        beforefn.apply(this, arguments); // 执行新函数，修正 this
        __self.apply(this, arguments); // 执行原函数
        return __self;
    }
};
Function.prototype.after = function(afterfn) {
    debugger;
    var __self = this;
    return function() {
        var ret = __self.apply(this, arguments);
        afterfn.apply(this, arguments);
        return ret;
    }
};
var func = function() {
    debugger;
    console.log(2);
    // return this; //this is windows
};

func = func.before(function() {
    console.log(1);
}).after(function() {
    console.log(3);
});

func();

/**
 print:
    function () {
        debugger;
        console.log(2);
        // return this; //this is windows
    }
    1
    2
    3
 */