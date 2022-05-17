package com.bailiban.kw.utils;
import java.util.UUID;

/**
 * 产生UUID随机字符串工具类
 */
public final class UuidUtil {
    private UuidUtil(){}
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

//    public static void main(String[] args) {
//        System.out.println(getUuid());
//    }
}
