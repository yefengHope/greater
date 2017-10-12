/**
 * 单例模式
 *      -- js
 * Created by 韩峰 on 2016/7/25.
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
var a = Singleton.getInstance( 'sven1' );

alert ( a === b ); // true


var makeSound = function( animal ){
    animal.sound();
};
var Duck = function(){}
Duck.prototype.sound = function(){
    console.log( '嘎嘎嘎' );
};
var Chicken = function(){}
Chicken.prototype.sound = function(){
    console.log( '咯咯咯' );
};
makeSound( new Duck() ); // 嘎嘎嘎
makeSound( new Chicken() ); // 咯咯咯

/*疑问改编*/
/*
var makeSound = function( animal ){
    animal.sound();
};
var Duck = function(){}
Duck.sound = function(){
    console.log( '嘎嘎嘎' );
};


makeSound( new Duck() );  //报错
输出:
    Uncaught TypeError: animal.sound is not a function(…)
 Duck.sound()       //正确
 输出: 嘎嘎嘎
*/


/**
 * 模拟出 public 和 private 这两种封装性
 * @type {{getName}}
 */
var myObject = (function(){
    var __name = 'sven'; // 私有（private）变量
    return {
        getName: function(){ // 公开（public）方法
            return __name;
        }
    }
})();
console.log( myObject.getName() ); // 输出：sven
console.log( myObject.__name ) // 输出：undefined