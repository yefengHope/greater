package com.hf.adminWeb.controller.demo;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>@Title Look for Spring access url and method mapping  </p>
 * <p>@Description 获取spring的访问url和方法映射</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/7/13 9:33 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Controller
public class GetRequestMappingController {

    private static Logger logger;
    static {
        logger = LoggerFactory.getLogger(GetRequestMappingController.class);
    }

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    RequestMappingHandlerMapping handlerMapping;

    @RequestMapping(value = "/getUrlMapping.do")
    @ResponseBody
    public Map getUrlMapping(HttpServletRequest request) {

        try {
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
            for (Iterator<RequestMappingInfo> iterator = handlerMethods.keySet().iterator();
                 iterator.hasNext();
                    ) {
                RequestMappingInfo info = iterator.next();
                StringBuilder sb = new StringBuilder();
                sb.append(info.getPatternsCondition().toString());
                sb.append(info.getProducesCondition().toString());
                // sb.append(info.getConsumesCondition());
                // sb.append(info.getCustomCondition());
                // sb.append(info.getHeadersCondition());
                // sb.append(info.getMethodsCondition());
                // sb.append(info.getParamsCondition());

                sb.append("===");

                HandlerMethod method = handlerMethods.get(info);
                sb.append(method.getMethod().getName() + "--");
                try {
                    sb.append(JSON.toJSONString(method.getMethodAnnotation(RequestMapping.class)));
                } catch (Exception e) {
                }
                logger.info(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
