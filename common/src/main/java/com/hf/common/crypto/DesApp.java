package com.hf.common.crypto;


import com.hf.common.encode.Base64App;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by *** on 2016/10/28.
 * DesApp for -
 * Copyright © 2008 - 2016 DGG Corporation, All Rights Reserved
 */
public class DesApp {
    private static byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};
    private static String cryptKey = "cd@dgg_9"; //密码


    /**
     * 默认加密
     * <p>@Description 使用默认密码的字符串 </p>
     * @param encryptString     加密字符
     * @return
     * @throws Exception
     */
    public static String encryptDESWrap(String encryptString) throws Exception {
        return encryptDES(encryptString,cryptKey);
    }

    /**
     * 默认解密
     * <p>@Description 使用默认密码的字符串 </p>
     * @param decryptString     解密字符
     * @return
     * @throws Exception
     */
    public static String decryptDESWrap(String decryptString) throws Exception {
        return decryptDES(decryptString,cryptKey);
    }


    /**
     * 加密
     * @param encryptString 加密字符
     * @param encryptKey    加密的key
     * @return
     * @throws Exception
     */
    public static String encryptDES(String encryptString, String encryptKey)
            throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("iso-8859-1"));
        return Base64App.encode(encryptedData);
    }

    /**
     * 解密
     * @param decryptString 解密字符串
     * @param decryptKey    解密的key
     * @return
     * @throws Exception
     */
    public static String decryptDES(String decryptString, String decryptKey)
            throws Exception {
        byte[] byteMi = Base64App.decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);
        return new String(decryptedData,"utf-8");
    }

}
