package com.hf.adminWeb.config.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Spring security 登录异常的处理，返回json（框架本身返回页面）
 * Created by rain on 2017/8/15.
 */
@Component
public class MyJsonFailAuthenticationSuccessHandler implements
        AuthenticationFailureHandler {

    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(MyJsonFailAuthenticationSuccessHandler.class);
    }

    /*  AuthenticationException常用的的子类：
            UsernameNotFoundException 用户找不到
            BadCredentialsException 坏的凭据

            AccountStatusException 用户状态异常它包含如下子类
            AccountExpiredException 账户过期
            LockedException
            账户锁定
            DisabledException 账户不可用
            CredentialsExpiredException 证书过期*/

    /**
     * Called when an authentication attempt fails.
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(JSON.toJSONString(parameterMap));
        // request.getRequestDispatcher("/login/failure.do").forward(request, response);
        // 因为不能使用以上语句，该方法就直接返回了，所以需要如下处理
        try {
            String info = "";
            String exceptionName = exception.getClass().getSimpleName();
            switch (exceptionName) {
                case "BadCredentialsException" : {info = "密码错误";} break;
                case "UsernameNotFoundException" : {info = "帐号不存在";} break;
                case "AccountExpiredException" : {info = "账户过期";} break;
                case "LockedException" : {info = "账户被锁定";} break;
                case "DisabledException" : {info = "账户不可用";} break;
                case "CredentialsExpiredException" : {info = "证书过期";} break;
                default: {
                    info = exceptionName;
                    logger.error("【登录异常】：未知错误",exception);
                }break;
            }
            response.setCharacterEncoding("utf-8");
            // response.setContentType("text/html; charset=UTF-8");
            response.setContentType("application/json; charset=UTF-8");
            JSONObject json = new JSONObject();
            json.put("status",false);
            json.put("info",info);
            json.put("loginStatus",false);
            json.put("loginInfo",exception);
            response.getWriter().print(json.toJSONString());
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
