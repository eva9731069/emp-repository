package com.util;

import java.util.UUID;

public class SaltUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
