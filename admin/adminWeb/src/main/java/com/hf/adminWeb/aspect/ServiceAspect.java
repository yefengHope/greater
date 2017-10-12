package com.hf.adminWeb.aspect;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * service层执行时间记录
 * Created by rain on 2017/8/20.
 */
@Aspect
@Component //这个必须有，因为我被坑了
public class ServiceAspect {
    private static Logger logger;

    static {
        logger = LoggerFactory.getLogger(ServiceAspect.class);
    }

    /*
        三次执行，执行次数：1000，10000，100000
        messagePrint        ：123ms  593ms   2481ms
        stringBuilderPrint  ：26ms   136ms   891ms
     */

    private static String messagePrint(Long ms ,String signature,String line){
        return MessageFormat.format("耗时:{0}ms ,方法名【{1}】{2}",ms,signature,line);
    }

    private static String stringBuilderPrint(Long ms ,Object signature,Object line) {
        StringBuilder sb = new StringBuilder();
        sb.append("service执行检测，耗时：");
        sb.append(ms);
        sb.append("ms ,方法名【");
        sb.append(signature);
        sb.append("】");
        sb.append(line);
        return sb.toString();
    }

    // private ThreadLocal<StopWatch> threadLocalClock = new ThreadLocal<>();

    // 拦截所有service
    @Pointcut("execution(* com.fengyu..service..*(..))")
    private void accessService(){}

    // 环绕通知
    @Around(value = "accessService()")
    private Object serviceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //用 commons-lang 提供的 StopWatch 计时，Spring 也提供了一个 StopWatch
        StopWatch clock = new StopWatch();
        clock.start(); //计时开始
        Object result =joinPoint.proceed();//执行目标方法
        clock.stop();  //计时结束

        //显示出方法原型及耗时
        Long ms = clock.getTime();
        if (ms > 200L) {
            logger.error(stringBuilderPrint(ms,joinPoint.getSignature(),0));
        }
        return result;
    }
    // @Before("accessService()")
    // public void serviceBefore(JoinPoint joinPoint) {
    //     //用 commons-lang 提供的 StopWatch 计时，Spring 也提供了一个 StopWatch
    //     StopWatch clock = new StopWatch();
    //     clock.start(); //计时开始
    //     threadLocalClock.set(clock);
    // }
    //
    // @After("accessService()")
    // public void serviceAfter(JoinPoint joinPoint) {
    //     StopWatch clock = threadLocalClock.get();
    //     // 耗时
    //     Long ms = clock.getTime();
    //
    //     // int line = joinPoint.getSourceLocation().getLine();
    //     // if (ms > 1000) {
    //     System.out.println(stringBuilderPrint(ms,joinPoint.getSignature(),0));
    //     clock.stop();
    //     // }
    // }

    // public static void main(String[] args) {
    //     // StopWatch clock = new StopWatch();
    //     // clock.start();
    //     // for (int i=0,s=1000;i<s;i++){
    //     //     System.out.println(stringBuilderPrint(100L,"method test","第2行"));
    //     // }
    //     // clock.stop();
    //     // System.out.println(clock.getTime() + "ms");
    //     // 26ms
    // }
}
