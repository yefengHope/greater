package com.example.controller;

import com.example.vo.UploadedFileVo;
import com.example.vo.UserFileVo;
import com.example.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Created by HF on 2017/10/16.
 */
@Controller
@RequestMapping(value = "/ajax")
public class AjaxFormController {


    @RequestMapping(value = "ajaxFile")
    public void ajaxFileForm(MultipartFile multipartFile, UserVo userVo) {

    }


    @RequestMapping(value = "/uploadUserAndFileRequest", method = RequestMethod.POST)
    @ResponseBody
    public void uploadUserAndFile(UserVo vo, @RequestParam(value = "image",required = false) MultipartFile image, HttpServletRequest request){
        System.out.println(vo);
    }

    @RequestMapping(value = "/uploadUserAndFile", method = RequestMethod.POST)
    @ResponseBody
    public void uploadUserAndFile(UserVo vo, MultipartFile image, HttpServletResponse response){
        System.out.println(vo);
    }

    @RequestMapping(value = "/uploadUserFileVo", method = RequestMethod.POST)
    @ResponseBody
    public void uploadUserFileVo(UserFileVo vo, HttpServletResponse response){
        System.out.println(vo);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(UserVo vo, MultipartHttpServletRequest request, HttpServletResponse response) {

        System.out.println(vo);

        //0. notice, we have used MultipartHttpServletRequest

        //1. get the files from the request object
        Iterator<String> itr = request.getFileNames();

        MultipartFile mpf = request.getFile(itr.next());
        System.out.println(mpf.getOriginalFilename() + " uploaded!");
        UploadedFileVo ufile = new UploadedFileVo();
        try {
            //just temporary save file info into ufile
            ufile.setLength(mpf.getBytes().length);
            ufile.setBytes(mpf.getBytes());
            ufile.setType(mpf.getContentType());
            ufile.setName(mpf.getOriginalFilename());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //2. send it back to the client as <img> that calls get method
        //we are using getTimeInMillis to avoid server cached image

        return "<img src='http://localhost:8080/spring-mvc-file-upload/rest/cont/get/" + Calendar.getInstance().getTimeInMillis() + "' />";

    }
}
