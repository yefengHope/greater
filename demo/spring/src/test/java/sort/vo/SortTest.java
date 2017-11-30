package sort.vo;

import com.alibaba.fastjson.JSON;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.util.StopWatch;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by 韩峰 on 2017/11/30.
 */
public class SortTest {

    public static void clone1() {
        SortVo sortVo = new SortVo();
        Random random = new Random();
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            for (int i = 0, is = 100000000; i < is; i++) {
                SortVo vo = sortVo.clone();
                vo.setName("张三" + i);
                vo.setName1("张三" + i);
                vo.setName2("张三" + i);
                vo.setName3("张三" + i);
                vo.setName4("张三" + i);
                vo.setName5("张三" + i);
                vo.setNum(random.nextInt());
            }
            watch.stop();
            System.out.println("clone 对象 话费时间");
            System.out.println(watch.getTotalTimeMillis());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public static void newObj() {
        Random random = new Random();
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0, is = 100000000; i < is; i++) {
            SortVo vo = new SortVo();
            vo.setName("张三" + i);
            vo.setName1("张三" + i);
            vo.setName2("张三" + i);
            vo.setName3("张三" + i);
            vo.setName4("张三" + i);
            vo.setName5("张三" + i);
            vo.setNum(random.nextInt());
        }
        watch.stop();
        System.out.println("new 对象 话费时间");
        System.out.println(watch.getTotalTimeMillis());
    }

    public static void sort() {
        Random random = new Random();
        List<SortVo> list = new ArrayList<>();

        SortVo comVo = new SortVo();

        for (int i = 0, is = 100000; i < is; i++) {
            SortVo vo = new SortVo();
            vo.setName("张三" + i);
            vo.setNum(random.nextInt());
            list.add(vo);

            if (i == 67437) {
                comVo = vo;
            }
        }
        StopWatch watch = new StopWatch();
        SortVo[] sortVos = new SortVo[list.size()];
        sortVos = list.toArray(sortVos);
        SortComtor comtor = new SortComtor();
        watch.start();
        Arrays.sort(sortVos,new SortComtor());
        watch.stop();
        System.out.println("排序时间");
        int index = Arrays.binarySearch(sortVos,comVo,new SortComtor());
        System.out.println("index:\t" + index);
        System.out.println(watch.getTotalTimeMillis());
        System.out.println(JSON.toJSONString(list));
    }
    /**
     * 执行结果，貌似差不多呢
     * @param args
     */
    public static void main(String[] args) {
//        clone1();
//        newObj();
        sort();
    }
}
