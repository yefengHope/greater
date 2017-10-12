package com.hf.adminService.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by HanFeng on 2017/7/28.
 */
public interface UserDetailsExtend extends UserDetails {

    String getLoginName();

    String getLoginNum();

}
