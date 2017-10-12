package com.hf.common.base;

/**
 * 获取Session中的用户
 * Created by HF on 2017/10/7.
 */
public interface ISessionUser {

    /**
     * 获取用户id
     * @return
     */
    String getUserId();

    /**
     * 获取用户name
     * @return
     */
    String getUserName();


}
