package com.example.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by HF on 2017/10/16.
 */
public class UserFileVo extends UserVo {
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
