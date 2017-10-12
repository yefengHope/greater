package com.hf.adminWeb.controller.demo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>@Title 类标题描述 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/7/12 9:46 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Controller
@RequestMapping(value = "/demo/freemarker/")
public class DemoFreemarkerController {

    @RequestMapping(value = "if")
    public String toIf(Model model) {
        model.addAttribute("user","李逵");
        JSONObject json = new JSONObject();
        json.put("url","www.baidu.com");
        json.put("name","baidu");
        model.addAttribute("latestProduct",json);
        return "demo/ifPage";
    }


    @RequestMapping(value = "list")
    public String tolist(Model model) {
        model.addAttribute("user","李逵");
        List<JSONObject> list = new ArrayList<>();

        JSONObject json1 = new JSONObject();
        // json1.put("name","余萍");
        json1.put("price","￥2.0");
        list.add(json1);

        JSONObject json2 =new JSONObject();
        json2.put("name","张瑞");
        json2.put("price","￥2.1");
        list.add(json2);
        model.addAttribute("animals",list);
        return "demo/listPage";
    }

}
