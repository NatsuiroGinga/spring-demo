package edu.sdau.spring_demo.utils;

import org.jetbrains.annotations.NotNull;

/**
 * @author ginga
 * @version 1.0
 * @ClassName MyStringUtils
 * @Date 12/11/2022 下午1:55
 */
public class MyStringUtils {

    // 私有构造方法
    private MyStringUtils() {
    }

    // 获取set方法名
    @NotNull
    public static String getSetterMethodByFiledName(String fieldName) {
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

}
