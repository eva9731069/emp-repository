package com.util;

import org.springframework.util.DigestUtils;

public class EncodeUtil {
    public static String md5(String src ,String salt){
        //這邊使用了springframework的加密方式
        //md5DigestAsHex參數是Bytes，所以透過java String類將字串轉為Bytes
        String result=src+ salt;
        return DigestUtils.md5DigestAsHex(result.getBytes());
    }
}
