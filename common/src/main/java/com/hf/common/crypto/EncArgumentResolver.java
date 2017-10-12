package com.hf.common.crypto;

import org.jboss.logging.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * <p>@Title 解密接口 </p>
 * <p>@Description 解密接口</p>
 * <p>@author hanfeng</p>
 * <p>@date 2016/11/30 10:52 创建日期</p>
 */
public class EncArgumentResolver implements HandlerMethodArgumentResolver {

    private static Logger logger = Logger.getLogger(EncArgumentResolver.class);

    // private static String paramField =

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //被EncRequest注解的参数才解密
        return methodParameter.getParameterAnnotation(EncRequest.class) != null;
    }
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer
            , NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // System.out.println("参数注解" + methodParameter.getParameterAnnotation(EncRequest.class));
        // System.out.println("参数名" + methodParameter.getParameterName());
        String parameterName = methodParameter.getParameterName(); //被注解的参数名
        String path = nativeWebRequest.getAttribute(parameterName, RequestAttributes.SCOPE_REQUEST) == null ? null : nativeWebRequest.getAttribute(parameterName,
                RequestAttributes.SCOPE_REQUEST).toString();
        if(StringUtils.isEmpty(path)) {
            path = nativeWebRequest.getParameter(parameterName);
        }
        if(StringUtils.isEmpty(path)) {
            logger.warn("加密信息为空");
            return null;
        }
        try {
            //DES算法中的加密和解密KEY 必须的8的倍数个字符
            path = DesApp.decryptDESWrap(path);//解密
        } catch (Exception e){
            logger.error("解密失败");
            path = null;
        }
        /**
         * 如果参数是一个bean,可以把解密得到的json字符串反序列化为一个对象返回
         */
        //Class<?> paramClass = methodParameter.getParameterType();
        //Object o = paramClass.newInstance();
        //o = mapper.readValue(content, paramClass);
        return path;
    }
}
