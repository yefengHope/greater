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
}
