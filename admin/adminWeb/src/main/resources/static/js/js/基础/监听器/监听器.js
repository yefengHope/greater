/*
    监听事件
    element.addEventListener(<event-name>, <callback>, <use-capture>);
*/


window.addEventLister("keydown", function() {
        console.log("你按下了一个按键")
    })
    //执行错误


/*  
    visual studio code 测试正常
    chrome 测试正常
*/
function keyDown(e) {
    /*兼容性代码
        e.which         FireFox和Opera
        event.keyCode   IE 
        e.charCode      FireFox
        
        详情请查看:  按键事件,浏览器和按键对象属性关系表
        */
    var currKey = 0,
        e = e || event;
    currKey = e.keyCode || e.which || e.charCode;
    var keyName = String.fromCharCode(currKey);
    console.log("按下按键:按键码: " + currKey + " 字符: " + keyName);
}
document.onkeydown = keyDown;

/*
    visual studio code 测试正常
    chrome 测试正常
*/
function keyUp(e) {
    var currKey = 0,
        e = e || event;
    currKey = e.keyCode || e.which || e.charCode;
    var keyName = String.fromCharCode(currKey);
    console.log("松开按键:按键码: " + currKey + " 字符: " + keyName);
}
document.onkeyup = keyUp;