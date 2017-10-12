package com.hf.common;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;

/**
 * 文件类
 * Created by rain on 2017/4/26.
 */
public class FileUtils {

    private static Logger logger = Logger.getLogger(FileUtils.class);

    /**
     * 获取绝对路径
     * @param name  "" : 当前类FileTest.class文件的URI目录
     *              "/" : 得到的是当前的classpath的绝对URI路径
     * @return {URL}
     */
    public static URL getResource(String name) {
        return FileUtils.class.getResource(name);
    }



    /**
     * 获取文件
     * @param pathName  路径名
     * @return {File}如果文件不存在返回空
     */
    public static File getFile(String pathName) {
        File file = new File(pathName);
        if (!file.exists()) {
            return null;
        }
        return file;
    }

    /**
     * 获取路径下的文件,如果不存在,则创建
     * @param pathName  路径名
     * @return  {File}
     */
    public static File getFileOrCreate(String pathName) {
        File file = new File(pathName);
        if (!file.exists()) {
            // 判断父级目录是否存在
            if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    logger.error("创建父级目录失败");
                }
            }

            // 创建文件
            try {
                if (file.createNewFile()){
                    logger.info("创建文件成功");
                } else {
                    logger.error("创建文件失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return file;
    }


    // public static void main(String[] args) {
    //     FileUtils fileUtils = new FileUtils();

        // 当前类FileTest.class文件的URI目录。不包括自己
        // file:/F:/workSpace/GitWork/chihuo/target/classes/com/fengyu/util/common/
        // logger.info(fileUtils.getResource(""));

        // 得到的是当前的classpath的绝对URI路径
        // file:/F:/workSpace/GitWork/chihuo/target/classes/
        // logger.info(fileUtils.getResource("/"));

        // File file = fileUtils.getFile("F:\\workSpace\\GitWork\\chihuo\\src\\main\\java\\com\\fengyu\\util\\common\\packSpringBoot.java");
        // File file1 = fileUtils.getFile("F:\\workSpace\\GitWork\\chihuo\\src\\main\\java\\com\\fengyu\\util\\common\\packSpringBoot1.java");
        // fileUtils.getFileOrCreate("F:\\workSpace\\GitWork\\chihuo\\src\\main\\java\\com\\fengyu\\util\\common\\packSpringBoot1.java");
        // logger.info(file.exists());
        // logger.info(file1.exists());

        // String pathName = "F:\\workSpace\\GitWork\\chihuo\\src\\main\\java\\com\\fengyu\\util\\common\\FileUtils.java";

        // 通过字节流流读取文件
        // try {
        //     InputStream inputStream = new FileInputStream(pathName);
        //     byte[] bytes = new byte[1024];
        //     while(inputStream.read(bytes) > 0) {
        //         logger.info(new String(bytes));
        //     }
        //     logger.info(inputStream.read());
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        // 通过字符流
        // try {
        //     Reader reader = new FileReader(pathName);
        //     char[] chars = new char[1024];
        //     while ( reader.read(chars) > 0) {
        //         logger.info(new String(chars));
        //     }
        //
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

    //     Reader reader = null;
    //     BufferedReader bufferedReader = null;
    //     // 通过字节流读取文件行
    //     try {
    //         reader = new FileReader(pathName);
    //         bufferedReader = new BufferedReader(reader);
    //         String lineStr = null;
    //         while (true) {
    //             lineStr = bufferedReader.readLine();
    //             if (lineStr == null) {
    //                 break;
    //             }
    //             logger.info(lineStr);
    //         }
    //     } catch (FileNotFoundException e) {
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     } finally {
    //         try {
    //             bufferedReader.close();
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //         try {
    //             reader.close();
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
}
