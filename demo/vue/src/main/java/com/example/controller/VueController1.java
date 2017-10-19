package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * vue的demo controller
 * Created by HF on 2017/10/15.
 */
@Controller
@RequestMapping(value = "/vue")
public class VueController1 {

    /**
     * vue的v-on:click和原生onclick有没有冲突
     * 结果： 先执行原生的onclick
     */
    @RequestMapping("eventListenOn")
    public void eventListenOn() {
    }

    /**
     * 组件小试牛刀
     */
    @RequestMapping("component")
    public void component(){

    }

    /**
     * vue watch实例内变量的改变
     */
    @RequestMapping("watch")
    public void watch(){}

    /**
     * vue 生命周期的钩子函数示例
     * created、mounted、updated、destroyed。
     * 钩子的 this 指向调用它的 Vue 实例
     */
    @RequestMapping("lifeCycle")
    public void lifeCycle(){

    }

    /**
     * 替换数组
     */
    @RequestMapping("arrayReplace")
    public void arrayReplace(){

    }/**
     * 替换对象
     */
    @RequestMapping("objReplace")
    public void objReplace(){

    }
}
