package com.hf.adminWeb.exception;

import com.alibaba.fastjson.JSONObject;
import com.hf.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * controller统一的异常捕获类
 * Created by rain on 2017/8/15.
 */
@ControllerAdvice
public class CommonException {
    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(CommonException.class);
    }


    @ExceptionHandler(value = {RuntimeException.class,Exception.class})
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handlerAllError(HttpServletRequest req, Exception e) {
        // 插入数据库
        JSONObject mapDb = buildInsertDbMap(req,e);

        // 返回客户端
        Map<String,Object> mapClient = new HashMap<>();
        mapClient.put("status",false);
        mapClient.put("info", "未知异常！" + e.getMessage());
        mapClient.put("data",e.getStackTrace());
        mapClient.put("url",null);
        return mapClient;
    }

    @ExceptionHandler(value = {BaseException.class,NullPointerException.class})
    @ResponseBody
    public Map<String, Object> handlerControllerError(HttpServletRequest req, Exception e) {
        // 插入数据库
        JSONObject mapDb = buildInsertDbMap(req,e);

        // 返回客户端
        Map<String,Object> mapClient = new HashMap<>();
        mapClient.put("status",false);
        mapClient.put("info", "参数异常！" + e.getMessage());
        mapClient.put("data",e.getStackTrace());
        mapClient.put("url",null);
        return mapClient;
    }

    /**
     * 处理数据验证异常
     * <p>@Validated(Update.class) @RequestBody Dog dog
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Map<String,Object> handleMethodArgumentNotValidException(HttpServletRequest req,MethodArgumentNotValidException e){
        logger.error(e.getMessage(), e);

        JSONObject mapDb = buildInsertDbMap(req,e);

        // 返回客户端
        Map<String,Object> mapClient = new HashMap<>();
        mapClient.put("status",false);
        mapClient.put("info", "参数缺失！" + e.getMessage());
        mapClient.put("data",e.getStackTrace());
        mapClient.put("url",null);
        return mapClient;
    }

    private JSONObject buildInsertDbMap(HttpServletRequest req, Exception e) {
        // 插入数据库
        JSONObject map = new JSONObject();
        map.put("tip", "此错误说明调用接口失败，失败原因见msg，如果msg为空，联系后台");
        map.put("path", req.getRequestURI());
        map.put("remoteAddr", req.getRemoteAddr());
        map.put("errorTile", e.getMessage());
        map.put("errorInfo",e.getStackTrace());
        map.put("params", req.getParameterMap());
        return map;
    }
}
