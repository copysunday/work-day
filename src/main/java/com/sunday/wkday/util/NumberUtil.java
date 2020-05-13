package com.sunday.wkday.util;

import java.math.BigDecimal;

public class NumberUtil {
    /**
     * 四舍五入保留newScale位小数
     * @param number
     * @param newScale 小数位
     * @return
     */
    public static String formatNum(BigDecimal number, int newScale) {
        return number.setScale(newScale, BigDecimal.ROUND_HALF_UP).toPlainString();
    }
}
