package com.hf.adminWeb.util.interceptor;

import com.hf.adminDao.entity.UserEntity;
import com.hf.adminWeb.util.common.TokenProcessor;
import com.hf.adminWeb.util.interceptor.annotation.FormToken;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * <p>@Title 防止重复提交过滤器 </p>
 * <p>@author hanfeng</p>
 * <p>@date 2016/11/3 16:18 创建日期</p>
 */
public class FormTokenInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = Logger.getLogger(FormTokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        // User user = UserUtil.getUser();
        UserEntity user = new UserEntity();
        if (user != null) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            FormToken annotation = method.getAnnotation(FormToken.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.needSaveToken();
                if (needSaveSession) {
                    request.getSession().setAttribute("token", TokenProcessor.createToken(TokenProcessor.NANOTIME));
                }

                boolean needRemoveSession = annotation.needRemoveToken();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        LOG.warn("请不要重复提交,[用户名:" + user.getName() + ",url:"
                                + request.getServletPath() + "]");
                        return false;
                    }
                    request.getSession().removeAttribute("token");
                }
            }
        }
        return true;
    }

    /**
     * 是否重复提交
     * @param request   request
     * @return {boolean}
     */
    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute("token");
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("token");
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
}
