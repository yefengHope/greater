/**
 * this只读对象测试
 * Created by 韩峰 on 2016/8/3.
 */

var obj = {
    x : 0,
    y: {
        x : 1,
        z : 2,
        t : this.x
    },
   getX : function () {
      console.log(this.x);
      console.log(this.y.t);
   }
};
obj.getX();
/*  obj.getX();
    输出:
        0
        undefined
*/



var x = obj.getX;
x();


/**
 * this丢失问题
 */
/*
错误案例:
<html>
<body>
    <div id="div1">我是一个 div</div>
     <script>
        var getId = document.getElementById;
        getId( 'div1' );
     </script>
</body>
</html>

错误原因:
 在 Chrome、Firefox、IE10 中执行过后就会发现，这段代码抛出了一个异常。这是因为许多
 引擎的 document.getElementById 方法的内部实现中需要用到 this 。这个 this 本来被期望指向
 document ，当 getElementById 方法作为 document 对象的属性被调用时，方法内部的 this 确实是指
 向 document 的。
 但当用 getId 来引用 document.getElementById 之后， 再调用 getId ， 此时就成了普通函数调用，
 函数内部的 this 指向了 window ，而不是原来的 document 。

修正代码如下:
*/
document.getElementById = (function( func ){
    return function(){
        return func.apply( document, arguments );
    }
})( document.getElementById );
var getId = document.getElementById;
var div = getId( 'div1' );
alert (div.id); // 输出： div1
