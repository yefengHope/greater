package com.hf.adminWeb.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 拦截url
 * Created by rain on 2017/8/20.
 */
@Aspect
@Component
public class UrlAccessAspect {
    
    
    private static Logger logger;
    static {
        logger = LoggerFactory.getLogger(UrlAccessAspect.class);
    }

    @Resource
    private RequestMappingHandlerMapping handlerMapping;

    /*
    before 目标方法执行前执行，前置通知
    after 目标方法执行后执行，后置通知
    after returning 目标方法返回时执行 ，后置返回通知
    after throwing 目标方法抛出异常时执行 异常通知
    around 在目标函数执行中执行，可控制目标函数是否执行，环绕通知
     */

    /*

    .. ：匹配方法定义中的任意数量的参数，此外还匹配类定义中的任意数量包
    ＋ ：匹配给定类的任意子类
    * ：匹配任意数量的字符

      类型签名表达式
      within(<type name>)
        //匹配com.zejian.dao包及其子包中所有类中的所有方法
        @Pointcut("within(com.zejian.dao..*)")
        //匹配UserDaoImpl类中所有方法
        @Pointcut("within(com.zejian.dao.UserDaoImpl)")
        //匹配UserDaoImpl类及其子类中所有方法
        @Pointcut("within(com.zejian.dao.UserDaoImpl+)")
        //匹配所有实现UserDao接口的类的所有方法
        @Pointcut("within(com.zejian.dao.UserDao+)")

        方法签名表达式
        execution(<scope> <return-type> <fully-qualified-class-name>.*(parameters))
        //匹配UserDaoImpl类中的所有公共方法并且返回值为int类型
        @Pointcut("execution(public int com.zejian.dao.UserDaoImpl.*(..))")

        //Spring AOP扩展的
        //匹配名称中带有后缀Service的Bean。
        @Pointcut("bean(*Service)")
        private void myPointcut1(){}

        // 用于匹配当前AOP代理对象类型的执行方法
        //匹配了任意实现了UserDao接口的代理对象的方法进行过滤
        @Pointcut("this(com.zejian.spring.springAop.dao.UserDao)")
        private void myPointcut2(){}

        //匹配了任意实现了UserDao接口的目标对象的方法进行过滤
        @Pointcut("target(com.zejian.spring.springAop.dao.UserDao)")
        private void myPointcut3(){}

        //匹配使用了MarkerAnnotation注解的方法(注意是方法)
        @Pointcut("@annotation(com.zejian.spring.annotation.MarkerAnnotation)")
        private void myPointcut5(){}
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void urlAccess(){

    }

    @Before(value="urlAccess()")
    public void accessUrlBefore(JoinPoint joinPoint) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.debug("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);

        // 获取处理方法的链接
        try {

            String urlPath = handlerMapping.getUrlPathHelper().getLookupPathForRequest(request);
            logger.debug(urlPath);

            // 查询登录缓存，获取可以访问的路径，如果在列表之外则拒绝访问！
            // 访问被拒绝的写入数据库


            // 防止用户刷页面，Redis记录访问的次数，不需要持久化到数据库
            // 如果需要根据数据库设置的访问权限限制

            // 获取当前cpu，内存状态，磁盘读取情况
            // 如果服务器状态差，则开启访问限制，优先会员访问


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @AfterReturning(value = "urlAccess()")
    // public void accessUrlAfter(JoinPoint joinPoint) {
    //
    // }
}
