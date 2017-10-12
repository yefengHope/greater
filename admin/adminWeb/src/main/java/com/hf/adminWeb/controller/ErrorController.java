package com.hf.adminWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HanFeng on 2017/7/28.
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    @RequestMapping(value = "ie.html")
    public String toIe() {
        return "error/ie";
    }
}
