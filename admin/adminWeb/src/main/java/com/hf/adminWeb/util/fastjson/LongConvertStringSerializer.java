package com.hf.adminWeb.util.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * fastJson 定制类 - 将类中的Long型id转换成String
 * Created by rain on 2017/8/10.
 */
public class LongConvertStringSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        jsonSerializer.write(String.valueOf(o));
    }
}
