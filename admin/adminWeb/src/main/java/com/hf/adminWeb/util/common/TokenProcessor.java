package com.hf.adminWeb.util.common;

/**
 * <p>@Title token生成 </p>
 * <p>@author hanfeng</p>
 * <p>@date 2016/11/3 16:31 创建日期</p>
 */
public class TokenProcessor {


    /**
     * todo 注意改进
     * 突然发现这个token生成器不知道怎么生成这个token的,
     * 于是将createToken改成createTokenNanoTime,
     * 我就想了啊,如果我在很多个地方都用了这个,那么我是不是在每一个用的地方都需要去改名字呢?
     * 那么我应该有一个统一的入口方法,通过参数决定调用什么要的token生成器
     */

    /**
     * 纳秒生成器
     */
    public final static Integer NANOTIME = 1;

    /**
     * token生成器
     * @param type  生成token的类型
     * @return {Long}
     */
    public static Long createToken(Integer type){
        Long token = null;
        switch (type) {
            case 1 : token = createTokenNanoTime(); break;
        }
        return token;
    }

    /**
     * token生成器
     * @param type      生成token的类型
     * @param profix    生成token的前缀
     * @param number    token后面是否添加计数器,n位后缀计数 {null:没有计数器,0:没有计数器}
     *                  todo 计数器还没有实现
     * @return
     */
    public static String createToken (Integer type,String profix,Integer number) {
        return null;
    }



    /**
     * 生成token
     * 获取当前系统纳秒级时间
     * @return
     */
    private static Long createTokenNanoTime() {
        Long timeMillis = System.nanoTime();//纳秒级
        return timeMillis;
    }
}
