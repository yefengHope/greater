/*****************
 *闭包和面对对象设计*
 ******************/

//--闭包--
function leijia() {
    var i = 0;
    return {
        call: function() {
            ++i;
            console.log(i);
        }
    }
}
var x = leijia();
x.call();
x.call();
x.call();

//--面对对象的闭包1--
var leijia2 = {
    i: 0,
    call: function() {
        ++this.i;
        console.log(this.i);
    }
}
leijia2.call();
leijia2.call();
leijia2.call();

//--面对对象的闭包2--
var Extent = function() {
    this.value = 0;
};
Extent.prototype.call = function() {
    this.value++;
    console.log(this.value);
};
var extent = new Extent();
extent.call();
extent.call();
extent.call();