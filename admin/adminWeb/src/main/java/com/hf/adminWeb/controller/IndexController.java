package com.hf.adminWeb.controller;

import com.alibaba.fastjson.JSON;
import com.hf.common.crypto.EncRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/7/17.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "/")
    public String indexVoid(HttpServletRequest request){
        return "index_simple";
    }

    @RequestMapping(value = "/index")
    public String index2(){
        return "index";
    }

    @RequestMapping(value = "/index_v3")
    public String index3(){
        return "index_v3";
    }

    @RequestMapping(value = "/layouts")
    public String layouts(){
        return "layouts";
    }

    @RequestMapping(value = "/api_access/redirect")
    public String redirectTest(String name, String id) {
        return "redirect:/user/all_page_list";
    }

    /**
     * url路径匹配表达式
     * <p>@description : 1.不能有//,特殊字符 2.由字母、数字、下划线或者1一个.组成  3.必须是字母、数字或者下划线结尾</p>
     * 案列1:/aaa/uer_list
     * 案列2:/aaa/uer_list.html
     */
    String patternUrl = "^(/(?=\\w)\\w+)+.?\\w+$";

    @RequestMapping(value = "/api_access/forward")
    public void forwardTest (@EncRequest String path , @EncRequest String name , HttpServletRequest request, HttpServletResponse response, Model model) {
        String url = path;
        try {
            boolean is = regexString(patternUrl,url);
            if (!is) {
                model.addAttribute("errorInfo","你想往哪里跳转!不会给你机会的");
                return;
            }
            // 案例
            // request.getRequestDispatcher("/user/all_page_list").forward(request,response);
            request.getRequestDispatcher(url).forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "你的页面找不到咯")
    @RequestMapping(value = "/api_access/forward1")
    public String forwardTest1 (String url ,HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean is = regexString(patternUrl,url);
        if (!is) {
            Map<String,Object> map = new HashMap<>();
            // model.addAttribute("errorInfo","你想往哪里跳转!不会给你机会的");
            map.put("errorInfo","你想往哪里跳!不会给你机会的");
            json(response,map,"UTF-8");
            return null;
        }
        String forwardUrl = "forward:" + url;
        return forwardUrl;
    }


    /**
     * 正则匹配
     * @param regex     正则表达式
     * @param str       被匹配的字符串
     * @return {boolean} 匹配成功,返回true;否则返回false;
     */
    public boolean regexString(String regex, String str) {
        boolean is = true;

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(regex);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(str);

        if (m.find()) {
            is = true;
        } else {
            is = false;
        }
        return is;
    }

    /**
     * 返回JSON格式数据
     * @param response
     * @param data 待返回的Java对象
     * @param encoding 返回JSON字符串的编码格式
     */
    public static void json(HttpServletResponse response, Object data, String encoding){
        //设置编码格式
        response.setContentType("text/plain;charset=" + encoding);
        response.setCharacterEncoding(encoding);

        PrintWriter out = null;
        try{
            out = response.getWriter();
            out.write(JSON.toJSONString(data));
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 异常页面控制
     * <p>说明:当这个Controller中任何一个方法发生异常，一定会被这个方法拦截到</p>
     * @param exception
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
//    public Map<String,Object> NotsuchExceptionHandler(Exception exception) {
    public Map<String,Object> nullPointerExceptionHandler(NullPointerException exception) {
        Map model = new TreeMap();
        model.put("status", false);
        model.put("info", "存在参数为空");
        model.put("errorInfo",exception.getMessage());
        return model;
    }
//    /**
//     * 异常页面控制
//     * <p>说明:当这个Controller中任何一个方法发生异常，一定会被这个方法拦截到</p>
//     * @param exception
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Map<String,Object> runtimeExceptionHandler(Exception exception) {
//        Map model = new TreeMap();
//        model.put("status", false);
//        return model;
//    }

}
