package com.hf.common.paramReturn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>@Title 参数返回类 </p>
 * <p>@Description 为了统一参数返回类型,可以使用@Autowired注入</p>
 * <p>@author hanfeng</p>
 * <p>@date 2016/12/9 10:52 创建日期</p>
 */
public class ParamsReturnSupport {


    /**
     * service层参数返回
     * <p>@description :
     *                 <p> 约定:   <code>state 返回状态</code> <br/><br/>
     *                      <code> MsgStatusDefine </code>默认参数定义类,详情请查看<br/>
     *
     *                      1. 0-100 为默认返回状态提示,如果有常用的默认参数请添加在后面<br/>
     *                      2. 默认参数<code> {0:"错误",1:"成功",2,:"空参提示",3:"跳转"} </code><br/>
     *                      3. 101及以上为根据开发者自定义参数<br/>
     *                 </p>
     *  </p>
     * @param state    返回状态         约定,请看说明
     * @param msg      返回状态提示
     * @param args     返回数据, 可变长参数
     * @return {Map{"state":"返回状态","msg":"返回状态提示","data":"返回数据"}}
     */
    public Map<String, Object> paramsReturn(Integer state, String msg, Object...args){
        Map<String, Object> map = new HashMap<>();
        map.put("state",state);
        map.put("msg",msg);
        map.put("data", Arrays.asList(args));
        return map;
    }

    /**
     * service/controller层参数返回,参数可包含url
     * <p>@description :
     *                 <p> 约定:   <code>state 返回状态</code> <br/><br/>
     *                      <code> MsgStatusDefine </code>默认参数定义类,详情请查看<br/>
     *
     *                      1. 0-100 为默认返回状态提示,如果有常用的默认参数请添加在后面<br/>
     *                      2. 默认参数<code> {0:"错误",1:"成功",2,:"空参提示",3:"跳转"} </code><br/>
     *                      3. 101及以上为根据开发者自定义参数<br/>
     *                 </p>
     *  </p>
     * @param state    返回状态         约定,请看说明
     * @param msg      返回状态提示
     * @param args     返回数据, 可变长参数
     * @return {Map{"state":"返回状态","msg":"返回状态提示","url":"url","data":"返回数据"}}
     */
    public Map<String, Object> paramsReturn(Integer state, String msg,String url, Object...args){
        Map<String, Object> map = new HashMap<>();
        map.put("state",state);
        map.put("msg",msg);
        map.put("url",url);
        map.put("data", Arrays.asList(args));
        return map;
    }

    // public static void main(String[] args) {
    //     System.out.println(JSONObject.toJSONString(serviceParamsReturn(1,"2")));
    // }
}
