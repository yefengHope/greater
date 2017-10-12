package com.hf.adminWeb.config.security;

import com.hf.adminService.service.RoleService;
import com.hf.adminService.service.SystemMenuService;
import com.hf.adminService.service.RoleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring security 登录成功的处理，返回json（框架本身返回页面）
 * Created by rain on 2017/8/15.
 */
@Component
public class MyJsonSuccesAuthenticationSuccessHandler implements
        AuthenticationSuccessHandler {

    // @Resource
    // private RoleService roleService;
    //
    // @Resource
    // private SystemMenuService systemMenuService;

    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {
        request.getRequestDispatcher("/admin/index.do").forward(request, response);

    }
}
