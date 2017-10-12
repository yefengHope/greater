/**
 * 输出格式
 * @param number 输出数量
 * @param str    输出字符
 * @param zj     间隔字符串
 */

function prin(number, str, zj) {
    if (zj === undefined) {
        zj = "\u0020"
    }
    var idt = str + zj; //空格
    var rstr = "";
    for (var i = 0; i < number; i++) {
        rstr = rstr + idt;
    }
    return rstr;
}
// prin(5, "*");


var sanjiao = {
    rowNumber: 10,
    rType: false, //false=倒三角 ,true=正三角 
    createZSJ: function(nub) {
        if (typeof(nub) !== "number") {
            console.log("nub type: must is number");
            return;
        }
        var total = nub;
        var str = "";
        for (var i = 0; i < total; ++i) {
            str = prin(total - i, "");
            str += prin(i + 1, "*");
            console.log("i:" + i + str);
        }
    },
    createDSJ: function(nub) {
        if (typeof(nub) !== "number") {
            console.log("nub type: must is number");
            return;
        }
        var total = nub;
        var str = "";
        for (var i = nub; i > 0; --i) {
            str = prin(total - i, "");
            str += prin(i, "*");
            console.log(str);
        }
    },
    createSJ: function() {
        if (this.rType === false) {
            this.createDSJ(this.rowNumber);
        } else {
            this.createZSJ(this.rowNumber);
        }
    }

}

sanjiao.rowNumber = 5;
// sanjiao.rType = false;
sanjiao.rType = true;
sanjiao.createSJ();

/**
 * nub type:string
 * nub type: must is number
 */
sj("wer");