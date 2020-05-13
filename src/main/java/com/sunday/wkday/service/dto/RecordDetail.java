package com.sunday.wkday.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecordDetail {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 备注
     */
    private String remark;

    /**
     * 工时，小时
     */
    private BigDecimal wkHour;


}
