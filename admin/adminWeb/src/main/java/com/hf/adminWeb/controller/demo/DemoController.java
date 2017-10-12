package com.hf.adminWeb.controller.demo;

import com.alibaba.fastjson.JSON;
import com.hf.adminDao.entity.UserEntity;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>@Title 类标题描述 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/2/10 11:14 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    @RequestMapping(value = "demo/bootstrap-validate")
    public String toBootStrapValidte() {
        return "demo/bootstrap-validate";
    }

    String[] name = {"及时雨", "黑旋风", "浪子", "玉麒麟", "母大虫", "豹子头", "青面兽"};
    int chat_index = 1;


    //------------------ bean validator start - 分割线 ------------------
    /**
     * demo 结果：根本不能进入controller方法
     * Validation failed for object='user'. Error count: 4
     * @param user
     */
    @RequestMapping(value = "beanValidated")
    public void beanValidated(@Validated UserEntity user) {
        System.out.println(JSON.toJSONString(user));
    }

    /**
     * 同上
     * @param user
     */
    @RequestMapping(value = "beanValid")
    public void beanValid(@Valid UserEntity user) {
        System.out.println(JSON.toJSONString(user));
    }

    /**
     * 会进入到该方法
     * @param user
     * @param bindingResult
     */
    @RequestMapping(value = "beanValidAndResult")
    public void beanValid(@Valid UserEntity user, BindingResult bindingResult) {
        // bindingResult 错误结果集输出方法
        System.out.println(JSON.toJSONString(bindingResult.getAllErrors()));
        System.out.println(JSON.toJSONString(user));
    }

    @RequestMapping(value = "beanNotValid")
    public void beanNotValid(UserEntity user) {
        System.out.println(JSON.toJSONString(user));
    }

    /*
        // 教程 url ： http://jinnianshilongnian.iteye.com/blog/1733708
        // 教程 url ： http://jinnianshilongnian.iteye.com/blog/1990081
        // ps : 同一个类，不同的校验场景。比如：新增和修改，修改必须校验id

        // 1. 分组验证
        public interface NAME() {}
        public interface AGE() {}

        // 通过@GroupSequence指定验证顺序：先验证NAME分组，
        // 如果有错误立即返回而不会验证AGE分组，接着如果NAME分组验证通过了，那么才去验证AGE分组，
        // 最后指定Student.class表示那些没有分组的在最后。这样我们就可以实现按顺序验证分组了
        @GroupSequence({NAME.class, AGE.class, Student.class})   // 顺序验证
        public class Student {
            //使用groups属性来给分组命名，然后在需要的地方指定命令即可
            @NotBlank(groups=NAME.class)
            private String name;
            @Min(value=3,groups=AGE.class)
            private int age;
            @NotBlank
            private String classess;
        }
        @RequestMapping(value="testStudent1")
        public void testStudent1(@Validated(NAME.class) Student student) {}

        @RequestMapping(value="testStudent2")
        public void testStudent2(@Validated({NAME.class,AGE.class})
        Student student) {}
     */

    //------------------ bean validator start - 分割线 ------------------


    /**
     * 聊天室系统
     *
     * @return
     */
    @RequestMapping(value = "demo/websocket/to_chat.htm")
    public ModelAndView toChat() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("demo/websocket/chat");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", chat_index++);
        // 随机中文名称
        int index = RandomUtils.nextInt(6,100);
        map.put("name", name[index]);
        mv.addObject("user", map);
        return mv;
    }
}
