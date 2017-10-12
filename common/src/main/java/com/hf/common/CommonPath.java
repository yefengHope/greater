package com.hf.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 获取路径
 *
 * 在Servlet中，"/"代表Web应用的跟目录。和物理路径的相对表示。
 * 例如：
 *      "./" 代表当前目录
 *      "../"代表上级目录。这种类似的表示，也是属于相对路径。
 * Created by 韩峰 on 2016/8/12.
 */
public class CommonPath {

    /**
     * 获取当前类的绝对路径
     * @return  path
     */
/*    public  static String curClassAbsolutePath(){
        File f = new File(Class.class.getClass().getResource("").getPath());
        System.out.println(f);
        return f.getPath();
    }*/

    /**
     * 获取当前类的所在工程路径
     * @return
     */
    public static String ClassProjectPath(){
        File f = new File(Class.class.getClass().getResource("/").getPath());
        return f.getPath();
    }

    /**
     * 获取工程名
     * 测试获取为空
     * @return
     */
    public static String webProjectName(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getContextPath();
    }

    /**
     * 获取工程路径
     * 测试获取到不知道什么鬼的东西 ,而且文件夹中没有文件,如下:
     * C:\Users\韩峰\AppData\Local\Temp\tomcat-docbase.7897902884063964425.8093\
     * @return
     */
    public static String webProjectPath(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession().getServletContext().getRealPath("/");
    }
}
