package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HF on 2017/10/30.
 */
@Controller
@RequestMapping(value = "/layui")
public class LayuiController {


    @RequestMapping(value = "/form")
    public String form() {
        return "layui/form";
    }

    @RequestMapping(value = "/list")
    public String list() {
        return "layui/list";
    }
}
