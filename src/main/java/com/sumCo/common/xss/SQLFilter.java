package com.sumCo.common.xss;

import org.apache.commons.lang.StringUtils;

import com.sumCo.common.exception.AppException;

/**
 * @author oplus
 * @Description: TODO(SQL過濾)
 * @date 2017-6-23 15:07
 */
public class SQLFilter {

    /**
     * SQL注入過濾
     * @param str  待驗證的字符串
     */
    public static String sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //轉換成小寫
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判斷是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new AppException("包含非法字符");
            }
        }

        return str;
    }
}
