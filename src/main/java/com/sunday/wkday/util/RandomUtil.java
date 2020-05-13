package com.sunday.wkday.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class RandomUtil {
    /**
     * 获取36位长度的UUID字符串
     *
     * @return
     */
    public static String getUUID36() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取32位长度的UUID字符串
     *
     * @return
     */
    public static String getUUID32() {
        return StringUtils.remove(getUUID36(), '-');
    }
}
