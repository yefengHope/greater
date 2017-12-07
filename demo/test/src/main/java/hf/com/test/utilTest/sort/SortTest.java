package hf.com.test.utilTest.sort;

import com.alibaba.fastjson.JSON;
import hf.com.test.utilTest.sort.vo.SortVo;
import org.springframework.util.StopWatch;

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

    public static void sort1() {
        Random random = new Random();
        List<SortVo> list = new ArrayList<>();

        SortVo comVo = new SortVo();
        StopWatch watch1 = new StopWatch();
        watch1.start();
        int is = 1000000 ;
        for (int i = 0; i < is; i++) {
            SortVo vo = new SortVo();
            vo.setName("张三" + i);
            vo.setNum(random.nextInt());
            list.add(vo);

            if (i == 67437) {
                comVo = vo;
            }
        }
        watch1.stop();
        System.out.printf("循环生成 %d 条数据时间 : %s 毫秒",is,watch1.getTotalTimeMillis());
        System.out.println();
        StopWatch watch = new StopWatch("1");
        SortVo[] sortVos = new SortVo[list.size()];
        sortVos = list.toArray(sortVos);
        SortComtor comtor = new SortComtor();
        watch.start();
        Arrays.sort(sortVos, comtor);
        watch.stop();
        System.out.printf("排序 %d 时间 : %s 毫秒",is,watch.getTotalTimeMillis());
        System.out.println();
        StopWatch watch2 = new StopWatch();
        watch2.start();
        int index = Arrays.binarySearch(sortVos, comVo, comtor);
        watch2.stop();
        System.out.println("index:\t" + index);
        System.out.printf("二分查找时间 %d 毫秒",watch2.getTotalTimeMillis());
        System.out.println();
    }

    public static void sort2() {
        Random random = new Random();
        List<SortVo> list = new ArrayList<>();

        SortVo comVo = new SortVo();

        SortVo vo3 = new SortVo();
        vo3.setName("张三3");
        vo3.setNum(3);
        list.add(vo3);

        SortVo vo6 = new SortVo();
        vo6.setName("张三");
        vo6.setNum(6);
        list.add(vo6);

        SortVo vo4 = new SortVo();
        vo4.setName("张三4");
        vo4.setNum(4);
        list.add(vo4);

        SortVo vo = new SortVo();
        vo.setName("张三");
        vo.setNum(1);
        list.add(vo);

        SortVo vo5 = new SortVo();
        vo5.setName("张三5");
        vo5.setNum(5);
        list.add(vo5);

        SortVo vo2 = new SortVo();
        vo2.setName("张三2");
        vo2.setNum(2);
        list.add(vo2);

        comVo.setName("张三3");
        comVo.setNum(3);

        SortVo[] sortVos = new SortVo[list.size()];
        sortVos = list.toArray(sortVos);
        SortComtor comtor = new SortComtor();
        Arrays.sort(sortVos, comtor);
        int index = Arrays.binarySearch(sortVos, comVo, comtor);
        System.out.println("index:\t" + index);
        System.out.println(JSON.toJSONString(list));
    }

    /**
     * 执行结果，貌似差不多呢
     *
     * @param args
     */
    public static void main(String[] args) {
//        clone1();
//        newObj();
        sort1();
//        sort2();
    }
}
