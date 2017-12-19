package hf.com.test.genericTest.basic;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayAlg {

    public static <T> T ArrayAlg1(T t) throws IllegalAccessException, InstantiationException {
        System.out.println(t.getClass().getName());
        return (T) t.getClass().newInstance();
    }

    public static <T> T ArrayAlg2(Class<T> c) throws IllegalAccessException, InstantiationException {
        System.out.println(c.getName());
        T t = c.newInstance();
        return t;
    }
    public static <T> void ArrayAlg3(List list) {
        System.out.println("list.getClass().getComponentType() :" + list.getClass().getComponentType());
    }

    public static <T> void ArrayAlg4(T... t ) {
        System.out.println("list.getClass().getComponentType() :" + t.getClass().getComponentType());
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
            ArrayAlg.ArrayAlg4("1","2","4");

        Map map = new HashMap();
        try {
            ArrayAlg.ArrayAlg1(map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        try {
            ArrayAlg.ArrayAlg2(HashMap.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
