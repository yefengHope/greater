package com.hf.demoSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 向导式Controller
 * Created by 韩峰 on 2017/11/13.
 */
@Controller
@RequestMapping("/wizard.htm")
//需要保存在session中的变量
@SessionAttributes("bean")
public class WazridControllerDemo {


    @RequestMapping
    public String step1(final ModelMap modelMap){
        modelMap.addAttribute("bean", "");
        return "step1";
    }

    @RequestMapping(params = "_step=2")
    public String step2(final @ModelAttribute("bean") Bean bean,
                        final Errors errors){
        return "step2";
    }

    @RequestMapping(params = "_step=3")
    public String step3(final @ModelAttribute("bean") Bean bean,
                        final Errors errors){
        return "step3";
    }

    @RequestMapping(params = "_finish")
    public String processFinish(@ModelAttribute("bean")Bean bean,
                                final Errors errors,
                                final ModelMap modelMap,
                                final SessionStatus status){

        status.setComplete();
        return "success";
    }

    @RequestMapping(params = "_cancel")
    public String processCancel(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final SessionStatus status){
        status.setComplete();
        return "cancel";
    }
}
