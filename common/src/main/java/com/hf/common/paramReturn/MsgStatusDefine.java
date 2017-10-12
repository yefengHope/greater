package com.hf.common.paramReturn;


/**
 * <p>@Title 消息默认状态定义 </p>
 * <p>@Description 包含默认参数定义,返回的默认状态 ,状态码在0-100范围内</p>
 * <p>@author hanfeng</p>
 * <p>@date 2016/12/9 10:54 创建日期</p>
 */
public class MsgStatusDefine {

    // TODO: 2016/12/9  状态码在0-100范围内

    /**
     * 错误
     */
    public final static Integer ERROR = 0;
    /**
     * 成功
     */
    public final static Integer SUCCESS = 1;
    /**
     * 空参提示
     */
    public final static Integer NULLTIP = 2;
    /**
     * 跳转
     */
    public final static Integer GOTO = 3;
}
