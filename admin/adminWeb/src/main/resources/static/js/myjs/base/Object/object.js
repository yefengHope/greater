/**
 * 对象基础
 * Created by rain on 2016/7/23.
 */
/**
  对象字面量语法
 {属性名: 属性值,属性名: 属性值,属性名: 属性值, ...}
 */
/*
{ x:2, z: 3}        属性名是标识符
{ "x":2, "z": 3}    属性名是字符串值
{ 'x':2, 'z': 3}    属性名是字符串值
{1:2, 3:4}          属性名是数值
*/

var obj = { x:2, y:3};
typeof obj;   //判断obj类型, 输出:"object"

/**
 *属性访问
 */
/*     属性 . 访问*/
var obj = { x:2, y:3};
obj.x;

var obj2 = {pos:{x:3 , y:4}}
obj2.pos;           //{ x: 3, y: 4 }
typeof obj2.pos;    //"object"
obj2.pos.x;

obj2.x = 12;        //覆盖已有的属性值,如果不存在属性则新建该属性并赋值;

/*  属性 [] 访问
  *     [] 内是需要访问的属性名的字符串值 */

var obj3 = {x:3 , "y":4}
obj3[x]  ; //ReferenceError: x is not defined
obj3["x"];       //3
obj3['x']        //3
obj3['y']        //4

var name = 'x';
obj3[name];      //3

obj3["x"] = 13;//覆盖已有的属性值,如果不存在属性则新建该属性并赋值;

/**
 * 方法
 */

/**
 * 克隆对象 -- 来自设计模式
 *      ECMAScript 5提供了 Object.create方法，可以用来克隆对象
 *
 *      使用案例:
 *       假设我们在编写一个飞机大战的网页游戏。某种飞机拥有分身技能，当它使用分身技能的时
 *       候，要在页面中创建一些跟它一模一样的飞机。如果不使用原型模式，那么在创建分身之前，无
 *       疑必须先保存该飞机的当前血量、炮弹等级、防御等级等信息，随后将这些信息设置到新创建的
 *       飞机上面，这样才能得到一架一模一样的新飞机。
 *       如果使用原型模式，我们只需要调用负责克隆的方法，便能完成同样的功能。
 */
var Plane = function(){
    this.blood = 100;
    this.attackLevel = 1;
    this.defenseLevel = 1;
};
var plane = new Plane();
plane.blood = 500;
plane.attackLevel = 10;
plane.defenseLevel = 7;
var clonePlane = Object.create( plane );
console.log( clonePlane ); // 输出：Object {blood: 500, attackLevel: 10, defenseLevel: 7}

/*在不支持 Object.create 方法的浏览器中，则可以使用以下代码：*/
Object.create = Object.create || function( obj ){
        var F = function(){};
        F.prototype = obj;
        return new F();
    }

/**
 * 函数式对象定义
 *      在这里 Person 并不是类，而是函数构造器，JavaScript 的函数既可以作为普通函数被调用，
 *      也可以作为构造器被调用。当使用 new 运算符来调用函数时，此时的函数就是一个构造器。 用
 *      new 运算符来创建对象的过程，实际上也只是先克隆 Object.prototype 对象，再进行一些其他额
 *      外操作的过程。
 * @param name
 * @constructor
 */
function Person( name ){
    this.name = name;
};
Person.prototype.getName = function(){
    return this.name;
};
var a = new Person( 'sven' )
console.log( a.name ); // 输出：sven
console.log( a.getName() ); // 输出：sven
console.log( Object.getPrototypeOf( a ) === Person.prototype ); // 输出：true
/**
 * 匿名函数式对象定义
 * @param name
 * @constructor
 */
var Singleton = function( name ){
    this.name = name;
    this.instance = null;
};
Singleton.prototype.getName = function(){
    alert ( this.name );
};
Singleton.getInstance = function( name ){
    if ( !this.instance ){
        this.instance = new Singleton( name );
    }
    return this.instance;
};