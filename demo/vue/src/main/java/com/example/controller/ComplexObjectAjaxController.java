package com.example.controller;

import com.example.vo.ComplexObjectVo;
import com.example.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 复杂对象ajax接受
 * Created by HF on 2017/10/16.
 */
@Controller
@RequestMapping("/complexObject")
public class ComplexObjectAjaxController {

    @RequestMapping(value = "complexObjectPage")
    public void complexObjectPage() {

    }

    @RequestMapping(value = "save")
    public void save(ComplexObjectVo vo, String name) {
        System.out.println(vo);
    }
}
