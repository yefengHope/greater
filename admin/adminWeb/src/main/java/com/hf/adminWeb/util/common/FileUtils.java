package com.fengyu.system.util.common;


import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;




/**
 * <p>@Title 文件工具类 </p>
 * <p>@Description 文件工具类</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2016/12/27 21:39 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class FileUtils {
    
    private static Logger logger = Logger.getLogger(FileUtils.class);

    private FileUtils() {
        new Throwable("二货这里不需要用 new ");
    }

    static Collection<File> listFiles(File root){
        List<File> files = new ArrayList<File>();
        listFiles(files, root);
        return files;
    }

    static void listFiles(List<File> files, File dir){
        File[] listFiles = dir.listFiles();
        if ( null == listFiles) {
            return;
        }
        for(File f: listFiles){
            if(f.isFile()){
                files.add(f);
            }else if(f.isDirectory()){
                listFiles(files, f);
            }
        }
    }


    static void run(Runnable task){
        long start = System.currentTimeMillis();
        task.run();
        logger.info("用时毫秒:" + String.valueOf( System.currentTimeMillis() - start ));
    }

    public static void main(String[] args){

        String folder = "C:\\Windows\\System32";
        final File dir = new File(folder);
        final Path path = Paths.get(folder);

        //listFiles()
        run(new Runnable(){
            public void run(){
                Collection<File> files = listFiles(dir);
                logger.info("通过listFiles()共发现" + files.size() + "个文件:");
            }
        });

        //java 7 nio.2
        run(new Runnable(){
            public void run(){
                final List<File> files = new ArrayList<>();
                SimpleFileVisitor<Path> finder = new SimpleFileVisitor<Path>(){
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        files.add(file.toFile());
                        return super.visitFile(file, attrs);
                    }
                };
                try{
                    java.nio.file.Files.walkFileTree(path, finder);
                }catch(IOException e){
                    //ignore
                }
                logger.info("通过 Java 7 NIO.2 共发现" + files.size() + "个文件");
            }
        });
    }
}
