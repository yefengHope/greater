/**
 * js痛定思痛深入学习
 * 变量的基础
 * Created by rain on 2016/7/23.
 */

var foo; //声明变量
console.log(foo);   //undefined,   只声明未赋值的变量,值都是undefined
foo = "abc"; //将字符串"abc"赋值给foo
foo = 123; //将数组123赋值给变量foo

/**
* 常量
*/
const FOO = 7;
// FOO = 8 ;  //报错
console.log(FOO);

const FOO;      //常量只声明会报错  SyntaxError: missing = in const declaration
console.log(FOO); //undefined