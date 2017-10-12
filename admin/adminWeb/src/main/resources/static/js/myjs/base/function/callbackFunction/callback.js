/***********
 * 回调函数 *
 **********/

var fun = function(callback) {
    console.log("开始测试函数:<<<<<<<<<<<<<<<<")
    if (typeof(callback) === 'function') {
        callback(5);
    }
}

function call(str) {
    console.log("我在执行回调函数:" + str);
}
fun(call);