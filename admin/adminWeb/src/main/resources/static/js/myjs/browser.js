/**
 * 判断浏览器
 */
function judgeBrowserOld() {
    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    if (window.ActiveXObject)
        Sys.ie = ua.match(/msie ([\d.]+)/)[1];
    else if (document.getBoxObjectFor)
        Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1];
    else if (window.MessageEvent && !document.getBoxObjectFor)
        Sys.chrome = ua.match(/chrome\/([\d.]+)/)[1];
    else if (window.opera)
        Sys.opera = ua.match(/opera.([\d.]+)/)[1];
    else if (window.openDatabase)
        Sys.safari = ua.match(/version\/([\d.]+)/)[1];

//以下进行测试
    if(Sys.ie) document.write('IE: '+Sys.ie);
    if(Sys.firefox) document.write('Firefox: '+Sys.firefox);
    if(Sys.chrome) document.write('Chrome: '+Sys.chrome);
    if(Sys.opera) document.write('Opera: '+Sys.opera);
    if(Sys.safari) document.write('Safari: '+Sys.safari);
}
/**
 * 判断浏览器
 */
function judgeBrowserNew() {
    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    var s;
    (s = ua.match(/rv:([\d.]+)\) like gecko/)) ? Sys.ie = s[1] :
        (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
            (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
                (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
                    (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
                        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

    if (Sys.ie) document.write('IE: ' + Sys.ie);
    if (Sys.firefox) document.write('Firefox: ' + Sys.firefox);
    if (Sys.chrome) document.write('Chrome: ' + Sys.chrome);
    if (Sys.opera) document.write('Opera: ' + Sys.opera);
    if (Sys.safari) document.write('Safari: ' + Sys.safari);
}

var ieVersion = {
    init : {
        top: null
    },
    isIE :  function (ver){
        var b = document.createElement('b');
        b.innerHTML = '<!--[if IE ' + ver + ']><i></i><![endif]-->';
        return b.getElementsByTagName('i').length === 1;
    }

};

/**
 * 判断IE版本
 *  只判断是否为ie var ie  = isIE()
 * @param ver           ie版本
 * @returns {boolean}
 */
 function isIE(ver){
    var b = document.createElement('b');
    b.innerHTML = '<!--[if IE ' + ver + ']><i></i><![endif]-->';
    return b.getElementsByTagName('i').length === 1;
}

if(isIE(6)){
    // IE 6
    alert("您的ie版本太低!!!")
}
// ...
if(isIE(9)){
    // IE 9
}




