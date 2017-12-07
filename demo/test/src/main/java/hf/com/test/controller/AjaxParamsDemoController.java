package hf.com.test.controller;

import com.alibaba.fastjson.JSON;
import hf.com.test.DTO.UserCopyDto;
import hf.com.test.DTO.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 韩峰 on 2017/12/7.
 */
@Controller
@RequestMapping(value = "/ajax")
public class AjaxParamsDemoController {

    @RequestMapping(value = "/multiObj")
    @ResponseBody
    public Object multiObj(UserDto user, UserCopyDto userDto) {
        List<Object> list = new ArrayList<>(Arrays.asList(user,userDto));
        return list;
    }

    @RequestMapping(value = "/user")
    @ResponseBody
    public Object user(UserDto user, HttpServletRequest request) {
        List<Object> list = new ArrayList<>(Arrays.asList(user));
        return list;
    }

    @RequestMapping(value = "/userParams")
    @ResponseBody
    public Object userParams(@RequestParam(value = "user",required = false) UserDto user,HttpServletRequest request) {
        List<Object> list = new ArrayList<>(Arrays.asList(user));
        System.out.println(JSON.toJSONString(request.getParameterMap()));
        return list;
    }

    @RequestMapping(value = "/userBody")
    @ResponseBody
    public Object userBody(@RequestBody  UserDto user) {
        List<Object> list = new ArrayList<>(Arrays.asList(user));
        return list;
    }
}
