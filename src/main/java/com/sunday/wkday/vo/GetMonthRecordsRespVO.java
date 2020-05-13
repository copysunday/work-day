package com.sunday.wkday.vo;

import lombok.Data;

import java.util.Map;

@Data
public class GetMonthRecordsRespVO {
    /**
     * 月总工时
     */
    private String sumMonthHour;
    /**
     * 每日工时
     */
    private Map<Integer, String> hourMap;
    /**
     * 备注
     */
    private Map<Integer, String> remarkMap;
}
